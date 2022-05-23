package sites.facebook;

import java.time.LocalDate;

public interface Config {
    String HOME_URL = "https://www.facebook.com";
    int FEMALE = 1;
    int MALE = 2;
    int CUSTOM = 3;

    LocalDate DATE_OF_BIRTH = LocalDate.now().minusYears(
            (int) ((Math.random() * (60 - 20)) + 20)
    ).plusDays(90); // someone from 20 to 60 years old

    String FIXED_PASSWORD = "dlkja89KJBG*&(,m49873";

}
