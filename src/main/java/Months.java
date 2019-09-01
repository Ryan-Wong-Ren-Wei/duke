public enum Months {
    Jan("January", "1"),
    Feb("February", "2"),
    Mar("March", "3"),
    April("April", "4"),
    May("May", "5"),
    June("June", "6"),
    July("July", "7"),
    Aug("August", "8"),
    Sep("September", "9"),
    Oct("October", "10"),
    Nov("November", "11"),
    Dec("December", "12");

    private final String month;
    private final String num;

    Months(String inputMonth, String inputNum){
        month = inputMonth;
        num = inputNum;
    }

    public String getMonth(){
        return month;
    }

    public int getNum(){
        return Integer.parseInt(num);
    }
}
