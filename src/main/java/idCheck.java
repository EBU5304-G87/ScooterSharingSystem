import java.util.Scanner;

public class idCheck {
    public static void main(String[] args){
        Database db = Database.getInstance();
        Scanner input = new Scanner(System.in);
        String id = input.next();
        boolean isUser = false;
        int i;

        for(i = 0; i < db.users.size(); i++){
            User u = db.users.get(i);
            if(u.getId() == Integer.parseInt(id)){
                isUser = true;
            }
        }

       // if(isUser == true && db.users.get(i).isBorrowed() == false && db.users.get(i).isViolation() == false){
         //   db.users.get(i).setBorrowed(true);
        Station a=new Station();
        int isScooterAway=a.unlockSlot(0);
        if(isScooterAway==1)
        {
            db.records.add(new Record(db.users.get(i).getId()));
            db.records.get(db.records.size() - 1).startRecord();
        }
        else
        {
            isUser=false;
        }
      //  }

        db.writeUsers();
    }
}