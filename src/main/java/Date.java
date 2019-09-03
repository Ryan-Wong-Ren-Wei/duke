public class Date {
    private String date;
    private String time;

    public Date(String dateAndTime){
        this.time = "";
        if (dateAndTime.contains("/")) {
            boolean hasTime = (dateAndTime.indexOf(' ') != -1);
            String dateEdited = dateAndTime;
            String day = dateAndTime.substring(0, dateEdited.indexOf('/'));

            dateEdited = dateAndTime.substring(dateEdited.indexOf('/') + 1);
            String month = dateEdited.substring(0, dateEdited.indexOf('/'));

            dateEdited = dateEdited.substring(dateEdited.indexOf('/') + 1);

            String year;
            String hours = "";
            String minutes = "";

            if (hasTime) {
                year = dateEdited.substring(0, dateEdited.indexOf(' '));
                dateEdited = dateEdited.substring(dateEdited.indexOf(' ') + 1);
                hours = dateEdited.substring(0, 2);
                minutes = dateEdited.substring(2);
            } else {
                year = dateEdited;
            }

            int dayInt = Integer.parseInt(day);
            if (dayInt < 4) {
                day = (dayInt == 3) ? "3rd" : (dayInt == 2) ? "2nd" : "1st";
            }

            int monthInt = Integer.parseInt(month);
            for (Months monthEnum : Months.values()) {
                if (monthEnum.getNum() == monthInt) {
                    month = monthEnum.getMonth();
                }
            }

            if (hasTime) {
                int hoursInt = Integer.parseInt(hours);
                if (hoursInt > 12) {
                    hoursInt -= 12;
                    time = Integer.toString(hoursInt) + "." + minutes + "pm";
                } else {
                    time = Integer.toString(hoursInt) + "." + minutes + "am";
                }
            }

            this.date = day + " of " + month + " " + year;
        } else {
            this.date = dateAndTime;
        }
    }

    public String toString(){
        if (time.equals("")) return date;
        else return date + ", " + time;
    }
}
