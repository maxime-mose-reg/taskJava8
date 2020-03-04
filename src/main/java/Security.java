import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

public class Security {
    private int id;
    private String code;
    @SerializedName("name_full")
    private String nameFull;
    private String cfi;
    @SerializedName("date_to")
    private LocalDate dateTo;
    @SerializedName("state_reg_date")
    private LocalDate stateRegDate;
    private State state;
    private Currency currency;

    public Currency getCurrency() {
        return currency;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public String getNameFull() {
        return nameFull;
    }

    public String getCode() {
        return code;
    }
}
