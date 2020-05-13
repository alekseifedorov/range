package my.assignment;

import my.assignment.service.impl.NumberRangeServiceImpl;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberRangeServiceTest {

    @Test
    public void shouldConvertNumberSequence() {
        NumberRangeServiceImpl numberRangeService = new NumberRangeServiceImpl();
        assertThat(numberRangeService.convert("1 2 3 4 7 8 9 11 13")).isEqualTo("1-4 7-9");
        assertThat(numberRangeService.convert("1 2 3 5 6 7 9 10 11 12")).isEqualTo("1-3 5-7 9-12");
        assertThat(numberRangeService.convert("1 3 5 6 7 9 11 12 13")).isEqualTo("5-7 11-13");
    }

    @Test
    public void shouldSkipSpaces() {
        NumberRangeServiceImpl numberRangeService = new NumberRangeServiceImpl();
        assertThat(numberRangeService.convert("  1 2 3 5  6 7 9 10 11 12 ")).isEqualTo("1-3 5-7 9-12");
    }
}

