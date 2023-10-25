import com.features.Car;
import com.features.CarDto;
import com.features.CarMapper;
import com.features.CarType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarMapperTest {

    @Test
    void carToCarDto() {

        //given
        Car car = new Car( "Morris", 5, CarType.SEDAN );

        //when
        CarDto carDto = CarMapper.INSTANCE.carToCarDto( car );

        //then
        assertNotNull( carDto );
        assertEquals( "Morris", carDto.getMake());
        assertEquals( 5, carDto.getSeatCount());
        assertEquals( "SEDAN", carDto.getType());
    }
}