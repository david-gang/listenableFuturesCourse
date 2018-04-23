package common;

import java.math.BigDecimal;
import java.math.MathContext;

public class SmartFibonacci {
    static final BigDecimal SQRT_5 = BigDecimal.valueOf(Math.sqrt(5));
    static final BigDecimal GR = BigDecimal.ONE.add(SQRT_5).divide(BigDecimal.valueOf(2));
    static final BigDecimal NGR = GR.negate().add(BigDecimal.ONE);

    public long calculate(int n) {

        if (n < 0) {
            return -1;
        }

        return BigDecimal.ONE.divide(SQRT_5, MathContext.DECIMAL128).multiply((GR.pow(n).subtract(NGR.pow(n)))).toBigInteger().longValue();
    }
}
