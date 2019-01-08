package eu.seatter.homeheating.edge.repository;

import eu.seatter.homeheating.edge.model.Measurement;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 * User: jas
 * Date: 08/01/2019
 * Time: 13:37
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class MeasurementRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MeasurementRepository measurementRepository;

    @Test
    public void whenFindById_thenReturnMeasurement() {
        //given
        Measurement measurement = new Measurement();
        measurement.setValue(10.0);
        entityManager.persist(measurement);
        entityManager.flush();

        //when
        Measurement found = measurementRepository.findById(measurement.getId()).orElse(new Measurement());

        //then
        assertThat(found.getId()).isEqualTo(measurement.getId());

    }

}