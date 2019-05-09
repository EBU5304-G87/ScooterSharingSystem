import java.util.Date;
import java.util.Scanner;

public class IdCheck {
    public static void main(String[] args){
       Database db = Database.getInstance();
       Scanner input = new Scanner(System.in);
       String id = input.next();
       boolean isUser = false;
       int i, num = 0;

       for(i = 0; i < db.users.size(); i++){
           User u = db.users.get(i);
           if(u.getId() == Integer.parseInt(id)){
               isUser = true;
               num = i;
           }
       }

        if(isUser && !db.users.get(num).isBorrowed() && !db.users.get(num).isViolation()) {
            db.users.get(num).setBorrowed(true);
            Station a=new Station();
            int isScooterAway=a.unlockSlot(0);
            if(isScooterAway==1) {
                db.records.add(new Record(db.users.get(num).getId(), new Date(0), new Date(0)));
                db.records.get(db.records.size() - 1).startRecord();
            } else {
                isUser = false;
            }
        }

       db.writeUsers();
    }
}
