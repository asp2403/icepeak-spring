package aspopov.icepeak.warehouse.service;

import aspopov.icepeak.warehouse.domain.*;
import aspopov.icepeak.warehouse.dto.ModelSearchParams;
import aspopov.icepeak.warehouse.repository.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class ModelServiceImplTest {

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private AgeRepository ageRepository;

    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private SkiRepository skiRepository;

    @Autowired
    private BootsRepository bootsRepository;

    @Autowired
    private ModelService modelService;

    private Model model_V1_A1_G1_P100;
    private Model model_V2_A1_G2_P200;
    private Model model_V2_A1_G2_P300;

    private Model model_B_V1;
    private Model model_B_V2;

    @BeforeAll
    public void setup() {
        var age1 = ageRepository.findById(1L).get();
        var age2 = ageRepository.findById(2L).get();

        var gender1 = genderRepository.findById(2L).get();
        var gender2 = genderRepository.findById(3L).get();

        var vendor1 = new Vendor(1, "Vendor1");
        var vendor2 = new Vendor(2, "Vendor2");
        vendorRepository.save(vendor1);
        vendorRepository.save(vendor2);

        model_V1_A1_G1_P100 = new Model(1, Category.SKI, "model_V1_A1_G1_P100", vendor1, gender1, age1,100);
        model_V2_A1_G2_P200 = new Model(2, Category.SKI,"model_V2_A1_G2_P200", vendor2, gender2, age1,200);
        model_V2_A1_G2_P300 = new Model(3, Category.SKI,"model_V2_A1_G2_P300", vendor2, gender2, age1,300);

        model_B_V1 = new Model(4, Category.BOOTS, "model_B_V1", vendor1, gender1, age1, 50);
        model_B_V2 = new Model(5, Category.BOOTS, "model_B_V2", vendor1, gender2, age2, 70);

        modelRepository.save(model_V1_A1_G1_P100);
        modelRepository.save(model_V2_A1_G2_P200);
        modelRepository.save(model_V2_A1_G2_P300);
        modelRepository.save(model_B_V1);
        modelRepository.save(model_B_V2);

        var ski11 = new Ski(11, model_V1_A1_G1_P100, 10, 0, 170);
        var ski12 = new Ski(12, model_V1_A1_G1_P100, 10, 0, 180);
        var ski13 = new Ski(11, model_V1_A1_G1_P100, 10, 0, 190);

        var ski21 = new Ski(21, model_V2_A1_G2_P200, 10, 0, 160);

        var ski31 = new Ski(31, model_V2_A1_G2_P300, 10, 0, 190);

        skiRepository.save(ski11);
        skiRepository.save(ski12);
        skiRepository.save(ski13);
        skiRepository.save(ski21);
        skiRepository.save(ski31);

        var boots1 = new Boots(1, model_B_V1, 1, 0, 25);
        var boots2 = new Boots(1, model_B_V1, 1, 0, 26);
        var boots3 = new Boots(1, model_B_V2, 1, 0, 27);

        bootsRepository.save(boots1);
        bootsRepository.save(boots2);
        bootsRepository.save(boots3);
    }


    @ParameterizedTest
    @MethodSource("generateData")
    @DisplayName("???????????? ?????????????????? ???????????? ????????????")
    public void shouldCorrectSearchProducts(ModelSearchParams searchParams, List<Model> expected) {
        var actual = modelService.search(searchParams);
        assertThat(actual.size()).isEqualTo(expected.size());
        actual.forEach((a) -> {
            assertThat(expected.stream().filter(item -> item.getId() == a.getId()).collect(Collectors.toList()).size()).isEqualTo(1);
        });

    }

    private Stream<Arguments> generateData() {
        var sp1 = new ModelSearchParams();
        sp1.setHeightFrom(160);
        sp1.setHeightTo(180);
        var l1 = List.of(model_V1_A1_G1_P100, model_V2_A1_G2_P200);
        var sp2 = new ModelSearchParams();
        sp2.setVendor(2L);
        var l2 = List.of(model_V2_A1_G2_P200, model_V2_A1_G2_P300);
        var sp3 = new ModelSearchParams();
        sp3.setPriceTo(200);
        var l3 = List.of(model_V1_A1_G1_P100, model_V2_A1_G2_P200, model_B_V1, model_B_V2);
        var sp4 = new ModelSearchParams();
        sp4.setModel("P100");
        var l4 = List.of(model_V1_A1_G1_P100);
        var sp5 = new ModelSearchParams();
        sp5.setAge(1L);
        var l5 = List.of(model_V1_A1_G1_P100, model_V2_A1_G2_P200, model_V2_A1_G2_P300, model_B_V1);
        var sp6 = new ModelSearchParams();
        sp6.setGender(2L);
        var l6 = List.of(model_V1_A1_G1_P100, model_B_V1);
        var sp7 = new ModelSearchParams();
        sp7.setCategory(Category.BOOTS);
        var l7 = List.of(model_B_V1, model_B_V2);
        var sp8 = new ModelSearchParams();
        sp8.setSizeFrom(25f);
        sp8.setSizeTo(26f);
        var l8 = List.of(model_B_V1);

        return Stream.of(
                Arguments.of(sp1, l1),
                Arguments.of(sp2, l2),
                Arguments.of(sp3, l3),
                Arguments.of(sp4, l4),
                Arguments.of(sp5, l5),
                Arguments.of(sp6, l6),
                Arguments.of(sp7, l7),
                Arguments.of(sp8, l8)
        );
    }
}