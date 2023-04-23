package domain;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;

@Data
@AllArgsConstructor
public final class Circular {
    private Color   color;
    private double radius;


    void scale(float factor) {
        // 200 lines of complex logic
        radius *= factor;
    }
}
