/**
 * @author jianjunhuang.me@foxmail.com
 * @since 2017/11/5
 */
public class Result {
    private int code;
    private String originStr;
    private String value;
    private String type;

    public Result(int code, String originStr, String value, String type) {
        this.code = code;
        this.originStr = originStr;
        this.value = value;
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getOriginStr() {
        return originStr;
    }

    public void setOriginStr(String originStr) {
        this.originStr = originStr;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        if ("int".equals(type)) {
            return "(" + code + "," + originStr + "," + Integer.parseInt(value) + "," + type + ")";
        } else if ("double".equals(type)) {
            return "(" + code + "," + originStr + "," + Double.parseDouble(value) + "," + type + ")";
        } else {
            return "(" + code + "," + originStr + "," + value + "," + type + ")";
        }
    }
}
