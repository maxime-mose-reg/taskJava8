import com.google.gson.annotations.SerializedName;

public class Currency {
    private int id;
    private String code;
    @SerializedName("name_short")
    private String nameShort;
    @SerializedName("name_full")
    private String nameFull;

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }
}
