/**
 * @author jianjunhuang.me@foxmail.com
 * @since 2017/11/5
 */
public class Err {
    private String errStr;
    private int pos;

    public Err(String errStr, int pos) {
        this.errStr = errStr;
        this.pos = pos;
    }

    public String getErrStr() {
        return errStr;
    }

    public void setErrStr(String errStr) {
        this.errStr = errStr;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    @Override
    public String toString() {
        return "第" + pos + "个字符\"" + errStr + "\"是非法字符";
    }
}
