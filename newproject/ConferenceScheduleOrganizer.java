import java.io.*;
import java.util.*;

class Person {
    private String name;
    private String email;

    public Person(String name, String email) {
        this.name = name;
        setEmail(email);
    }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) {
        if (!email.contains("@")) throw new IllegalArgumentException("Invalid email");
        this.email = email;
    }
}

class Speaker extends Person {
    private String topic;
    public Speaker(String name, String email, String topic) {
        super(name, email);
        this.topic = topic;
    }
    public String getTopic() { return topic; }
}

class Session {
    private int id;
    private String title;
    private String room;
    private String date;
    private String time;
    private Speaker speaker;

    public Session(int id, String title, String room, String date, String time, Speaker speaker) {
        this.id = id; this.title = title; this.room = room; this.date = date; this.time = time; this.speaker = speaker;
    }
    public int getId() { return id; }
    public void setTitle(String title) { this.title = title; }
    public String toFileString() {
        return id+","+title+","+room+","+date+","+time+","+speaker.getName()+","+speaker.getEmail()+","+speaker.getTopic();
    }
    public void displayInfo() {
        System.out.println("[Regular] ID:"+id+" | "+title+" | "+date+" "+time+" | "+room+" | Speaker: "+speaker.getName());
    }
}

class WorkshopSession extends Session {
    public WorkshopSession(int id, String title, String room, String date, String time, Speaker speaker) {
        super(id,title,room,date,time,speaker);
    }
    @Override
    public void displayInfo() { System.out.print("[Workshop] "); super.displayInfo(); }
}

public class ConferenceScheduleOrganizer {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Session> sessions = new ArrayList<>();
    static final String FILE = "sessions.txt";

    public static void main(String[] args) {
        loadFromFile();
        while(true) {
            try {
                menu();
                int ch = Integer.parseInt(sc.nextLine());
                switch(ch) {
                    case 1: addSession(); break;
                    case 2: viewSessions(); break;
                    case 3: updateSession(); break;
                    case 4: deleteSession(); break;
                    case 5: exportCSV(); break;
                    case 6: importCSV(); break;
                    case 7: saveToFile(); System.out.println("Saved. Bye!"); return;
                    default: System.out.println("Invalid choice");
                }
            } catch(Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    static void menu() {
        System.out.println("\n=== Conference Organizer ===");
        System.out.println("1. Add Session");
        System.out.println("2. View Sessions");
        System.out.println("3. Update Session Title");
        System.out.println("4. Delete Session");
        System.out.println("5. Export CSV");
        System.out.println("6. Import CSV");
        System.out.println("7. Save & Exit");
        System.out.print("Choose: ");
    }

    static void addSession() {
        System.out.print("ID: "); int id = Integer.parseInt(sc.nextLine());
        System.out.print("Title: "); String title = sc.nextLine();
        if(title.isEmpty()) throw new IllegalArgumentException("Title cannot be empty");
        System.out.print("Room: "); String room = sc.nextLine();
        System.out.print("Date (YYYY-MM-DD): "); String date = sc.nextLine();
        System.out.print("Time: "); String time = sc.nextLine();
        System.out.print("Speaker Name: "); String name = sc.nextLine();
        System.out.print("Speaker Email: "); String email = sc.nextLine();
        System.out.print("Topic: "); String topic = sc.nextLine();
        Speaker sp = new Speaker(name,email,topic);
        sessions.add(new WorkshopSession(id,title,room,date,time,sp));
        System.out.println("Added.");
    }

    static void viewSessions() {
        if(sessions.isEmpty()) { System.out.println("No sessions."); return; }
        for(Session s: sessions) s.displayInfo();
    }

    static void updateSession() {
        System.out.print("Enter ID: "); int id = Integer.parseInt(sc.nextLine());
        for(Session s: sessions) {
            if(s.getId()==id) {
                System.out.print("New Title: "); s.setTitle(sc.nextLine());
                System.out.println("Updated."); return;
            }
        }
        System.out.println("Not found.");
    }

    static void deleteSession() {
        System.out.print("Enter ID: "); int id = Integer.parseInt(sc.nextLine());
        sessions.removeIf(s -> s.getId()==id);
        System.out.println("Deleted if existed.");
    }

    static void saveToFile() {
        try(PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
            for(Session s: sessions) pw.println(s.toFileString());
        } catch(IOException e) { System.out.println("Save error"); }
    }

    static void loadFromFile() {
        File f = new File(FILE);
        if(!f.exists()) return;
        try(Scanner fs = new Scanner(f)) {
            while(fs.hasNextLine()) {
                String[] p = fs.nextLine().split(",");
                if(p.length>=8) {
                    Speaker sp = new Speaker(p[5],p[6],p[7]);
                    sessions.add(new WorkshopSession(Integer.parseInt(p[0]),p[1],p[2],p[3],p[4],sp));
                }
            }
        } catch(Exception e) { }
    }

    static void exportCSV() {
        saveToFile();
        System.out.println("Exported to sessions.txt / CSV-style file.");
    }

    static void importCSV() {
        sessions.clear();
        loadFromFile();
        System.out.println("Imported from sessions.txt");
    }
}
