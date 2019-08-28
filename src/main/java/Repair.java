import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor@AllArgsConstructor

public class Repair {
    private int id;
    private LocalDateTime addDate;
    private boolean accomplished;
    private LocalDateTime repairDate;
    private String contents;
    private int carId;

    public Repair(LocalDateTime addDate, String contents, int carId) {
        this.addDate = addDate;
        this.contents = contents;
        this.carId = carId;
    }
}
