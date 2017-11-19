import java.util.ArrayList;

/**
 * @author jianjunhuang.me@foxmail.com
 * @since 2017/11/5
 */
public class LexicalAnalysis {

    private String expression;
    private int expLength;

    public static final int ADD_CODE = 1;
    public static final int SUB_CODE = 2;
    public static final int MULTI_CODE = 3;
    public static final int DIV_CODE = 4;
    public static final int INT_CODE = 5;
    public static final int LEFT_BRACKETS_CODE = 6;
    public static final int RIGHT＿BRACKETS_CODE = 7;
    public static final int DOUBLE_CODE = 8;

    public static final char ADD = '+';
    public static final char SUB = '-';
    public static final char MULTI = '*';
    public static final char DIV = '/';
    public static final char LEFT_BRACKETS = '(';
    public static final char RIGHT_BRACKETS = ')';
    public static final char DECIMAL_POINT = '.';

    public static final String INT_TYPE = "int";
    public static final String DOUBLE_TYPE = "double";

    private ArrayList<Result> mResultList = new ArrayList<>();
    private ArrayList<Err> mErrList = new ArrayList<>();

    private int index = 0;

    public void analysis(String exp) {
        clearCache();
        this.expression = exp.trim();
        expLength = expression.length();
        int begin = 0;
        index = 0;
        while (index < expLength) {
            //1 ~ 9
            if (isNotZeroNum(index)) {
                begin = index;
                passNum();
                index++;
                if (index < expLength && isDecimalPoint(index)) {
                    index++;
                    if (index < expLength && isNum(index)) {
                        passNum();
                        index++;
                        if (index <= expLength) {
                            mResultList.add(new Result(DOUBLE_CODE, expression.substring(begin, index), expression.substring(begin, index), DOUBLE_TYPE));
                        }
                    } else {
                        mErrList.add(new Err(expression.substring(begin, index), index));
                    }
                } else {
                    mResultList.add(new Result(INT_CODE, expression.substring(begin, index), expression.substring(begin, index), INT_TYPE));
                }
                //0
            } else if (isZero(index)) {
                begin = index;
                index++;
                if (index < expLength) {
                    //
                    if (isNum(index)) {
                        passNum();
                        if (index + 1 < expLength && isDecimalPoint(index + 1)) {
                            index++;
                            passNum();
                        }
                        index++;
                        mErrList.add(new Err(expression.substring(begin, index), begin));
                    } else if (index < expLength && isDecimalPoint(index)) {

                        if (index + 1 < expLength) {
                            if (isNum(index + 1)) {
                                passNum();
                                index++;
                                if (index <= expLength) {
                                    mResultList.add(new Result(DOUBLE_CODE, expression.substring(begin, index), expression.substring(begin, index), DOUBLE_TYPE));
                                }
                            } else {
                                mResultList.add(new Result(INT_CODE, expression.substring(begin, index), expression.substring(begin, index), INT_TYPE));
                            }
                        } else {
                            mResultList.add(new Result(INT_CODE, expression.substring(begin, index), expression.substring(begin, index), INT_TYPE));
                        }
                    } else {
                        mResultList.add(new Result(INT_CODE, expression.substring(begin, index), expression.substring(begin, index), INT_TYPE));
                    }
                } else {
                    mResultList.add(new Result(INT_CODE, expression.substring(begin, expLength), expression.substring(begin, expLength), INT_TYPE));
                }

            } else {
                analysisOp(index);
            }
        }

        printResult();

    }

    private void clearCache() {
        if (mResultList.size() > 0) {
            mResultList.clear();
        }
        if (mErrList.size() > 0) {
            mErrList.clear();
        }
    }

    private void printResult() {
        for (int i = 0; i < mResultList.size(); i++) {
            System.out.println(mResultList.get(i).toString());
        }
        for (int j = 0; j < mErrList.size(); j++) {
            System.out.println(mErrList.get(j).toString());
        }
    }

    private boolean isDecimalPoint(int pos) {
        return expression.charAt(pos) == DECIMAL_POINT;
    }

    private void passNum() {
        while (index + 1 < expLength) {
            if (isNum(index + 1)) {
                index++;
            } else {
                break;
            }
        }
    }

    private void passZero() {
        while (index < expLength && isZero(index)) {
        }
    }


    private void analysisOp(int pos) {
        int code = -1;
        char ch = expression.charAt(pos);
        switch (ch) {
            case ADD: {
                code = ADD_CODE;
                break;
            }
            case SUB: {
                code = SUB_CODE;
                break;
            }
            case MULTI: {
                code = MULTI_CODE;
                break;
            }
            case DIV: {
                code = DIV_CODE;
                break;
            }
            case LEFT_BRACKETS: {
                code = LEFT_BRACKETS_CODE;
                break;
            }
            case RIGHT_BRACKETS: {
                code = RIGHT＿BRACKETS_CODE;
                break;
            }
            default: {
                break;
            }
        }
        if (code != -1) {
            mResultList.add(new Result(code, String.valueOf(ch), "NULL", "NULL"));
        } else {
            mErrList.add(new Err(String.valueOf(ch), pos));
        }
        index++;
    }

    private boolean isNum(int pos) {
        char ch = expression.charAt(pos);
        return ch >= '0' && ch <= '9';
    }

    private boolean isZero(int pos) {
        char ch = expression.charAt(pos);
        return ch == '0';
    }

    private boolean isNotZeroNum(int pos) {
        char ch = expression.charAt(pos);
        return ch > '0' && ch <= '9';
    }


}
