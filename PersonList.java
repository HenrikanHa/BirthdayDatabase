import java.io.*;
import java.util.Iterator;

public class PersonList {
    private LinkedList<Person> list = new LinkedList<Person>();

    public boolean add(Person p) {
        if (list.contains(p)) {
            return false;
        }
        list.add(p);
        return true;
    }

    public boolean addAlpha(Person p) {
        if (list.contains(p)) {
            return false;
        }
        list.addAlpha(p);
        return true;
    }

    public Person search(Person p) {
        for (Person person : list) {
            if (person.equals(p)) {
                return person;
            }
        }
        return null;
    }

    public boolean delete(Name n) {
        Iterator<Person> iterator = list.iterator();
        while (iterator.hasNext()) {
            Person p = iterator.next();
            if (p.getName().equals(n)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public String hasBirthdayOn(Date date) {
        for (Person person : list) {
            if (person.getDate().equals(date)) {
                return person.toString();
            }
        }
        return "Nobody has a birthday on that date.";
    }

    public String printList() {
        String result = "";
        for (Person person : list) {
            result += person.toString() + "\n";
        }
        return result;
    }

    public String sortAlphab() {
        return printList();
    }

    public String sortChronol() {
        LinkedList<Person> newList = new LinkedList<Person>(new ChronoComparator());
        for (Person person: list) {
        	newList.add(person);
        }
        newList.sort();
        String result = "";
        for (Person person : newList) {
            result += person.toString() + "\n";
        }
        return result;
    }

    public String findPersonByName(Name name) {
        for (Person person : list) {
            if (person.getName().equals(name)) {
                return person.toString();
            }
        }
        return "Person not found.";
    }

    public String saveToFile(String fileName) {
        String messageFromSave = "";
        try {
            ObjectOutputStream oOS = new ObjectOutputStream(new FileOutputStream(fileName));
            for (int i = 0; i < list.size(); i++) {
                oOS.writeObject(list.get(i));
            }
            oOS.flush();
            oOS.close();
        } catch (Exception e) {
            messageFromSave = e.toString();
        }
        return messageFromSave;
    }

    public String loadFromFile(String fileName) {
        String toReturn = "";
        try {
            ObjectInputStream oIS = new ObjectInputStream(new FileInputStream(fileName));
            while (oIS.available() > -1) {
                Person fromFile = (Person) (oIS.readObject());
                Person found = search(fromFile);
                if (found == null) {
                    if (add(fromFile)) {
                        toReturn += fromFile + "\n";
                    } else {
                        toReturn += fromFile + " not successfully added to DB.\n";
                    }
                } else {
                    toReturn += found + " already in the DB.\n";
                }
            }
            oIS.close();
        } catch (EOFException eOF) {
        } catch (Exception e) {
            toReturn += e;
        }
        return toReturn;
    }
}
