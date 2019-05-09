import java.util.Scanner;

public class idCheck {
    public static void main(String[] args){
        Database db = Database.getInstance();
        System.out.println("please enter your id: ");
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

        if(isUser == true && db.users.get(num).isBorrowed() == false && db.users.get(num).isViolation() == false){
            db.users.get(num).setBorrowed(true);
            System.out.println("here");
            Station a=new Station();
            int isScooterAway=a.unlockSlot(0);
            if(isScooterAway==1)
            {
                System.out.println("here");
                db.records.add(new Record(db.users.get(num).getId()));
                db.records.get(db.records.size() - 1).startRecord();
            }
            else
            {
                isUser=false;
            }
        }

        db.writeUsers();
    }
}