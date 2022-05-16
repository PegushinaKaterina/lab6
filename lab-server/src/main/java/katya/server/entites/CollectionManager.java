package katya.server.entites;
import katya.common.entites.HumanBeing;
import katya.server.util.workingWithCommand.FileWorker;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class CollectionManager {
    private static long idCounter = 1;
    private Date creationDate;
    private LinkedList<HumanBeing> collectionHumanBeing = new LinkedList<HumanBeing>();
    private FileWorker fileWorker;

    public CollectionManager(FileWorker fileWorker) throws FileNotFoundException {
        creationDate = new Date();
        this.fileWorker = fileWorker;
    }

    public LinkedList<HumanBeing> getCollectionHumanBeing() {
        return collectionHumanBeing;
    }

    public FileWorker getFileWorker() {
        return fileWorker;
    }

    public String info() {
        return "Информация о коллекции:"
                + "\nКласс коллекции: " + collectionHumanBeing.getClass().toString()
                + "\nДата создания: " + creationDate
                + "\nРазмер коллекции: " + collectionHumanBeing.size()
                + "\nКласс экземпляров коллекции" + HumanBeing.class;
    }

    public String show() {
        StringBuilder string = new StringBuilder();
        if (collectionHumanBeing.isEmpty()) {
            throw new IllegalArgumentException("Коллекция пуста");
        } else {
            for (int i = 0; i < collectionHumanBeing.size(); i++) {
                string.append(collectionHumanBeing.get(i).toString()).append("\n");
            }
        }
        return string.toString();
    }

    public String add(HumanBeing humanBeing) {
        humanBeing.setId(idCounter++);
        humanBeing.setCreationDate(new Date());
        collectionHumanBeing.add(humanBeing);
        collectionHumanBeing = collectionHumanBeing
                .stream()
                .sorted()
                .collect(Collectors.toCollection(LinkedList::new));
        return "\n Человек успешно добавлен";
    }

    public String update(long id, HumanBeing element) {
        if (collectionHumanBeing.isEmpty()) {
            throw new IllegalArgumentException("Коллекция пуста");
        } else {
            boolean found = false;
            for (int i = 0; i < collectionHumanBeing.size(); i++) {
                if (collectionHumanBeing.get(i).getId() == id) {
                    collectionHumanBeing.set(i, element);
                }
                found = true;
                break;
            }
            collectionHumanBeing = collectionHumanBeing
                    .stream()
                    .sorted()
                    .collect(Collectors.toCollection(LinkedList::new));
            if (!found) {
                throw new IllegalArgumentException("Элементов со значением id = " + id + " не найдено");
            } else {
                return "Значение элемента коллекции, id которого равен " + id + " успешно обновлено";
            }
        }
    }

    public String removeById(Long id) {

        if (collectionHumanBeing.isEmpty()) {
            throw new IllegalArgumentException("Коллекция пуста");
        } else {
            boolean found = false;
            for (int i = 0; i < collectionHumanBeing.size(); i++) {
                if (collectionHumanBeing.get(i).getId() == id) {
                    collectionHumanBeing.remove(i);
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new IllegalArgumentException("Элементов со значением id = " + id + " не найдено");
            } else {
                return "Элемент со значением id = " + id + " успешно удален";
            }
        }
    }

    public String clear() {
        if (collectionHumanBeing.isEmpty()) {
            return "Коллекция уже пуста";
        } else {
            collectionHumanBeing.clear();
            return "Коллекция успешно очищена";
        }

    }

    public String removeHead() {
        if (collectionHumanBeing.isEmpty()) {
            throw new IllegalArgumentException("Коллекция пуста");
        } else {
            return "Первый элемент коллекции: " + collectionHumanBeing.poll() + "\nуспешно удален";
        }
    }

    public String removeLover(HumanBeing humanBeing) {
        if (collectionHumanBeing.isEmpty()) {
            throw new IllegalArgumentException("Коллекция пуста");
        } else {
            boolean found = false;
            for (int i = 0; i < collectionHumanBeing.size(); i++) {
                if (collectionHumanBeing.get(i).compareTo(humanBeing) == -1) {
                    collectionHumanBeing.remove(i);
                    found = true;
                } else {
                    break;
                }
            }
            if (!found) {
                throw new IllegalArgumentException("Элементов, меньших, чем заданный, не найдено");
            } else {
                return "Элементы, меньшие, чем заданный, успешно удалены";
            }
        }
    }

    public String removeAllByMinutesOfWaiting(int minutesOfWaiting) {
        if (collectionHumanBeing.isEmpty()) {
            throw new IllegalArgumentException("Коллекция пуста");
        } else {
            boolean found = false;
            for (int i = 0; i < collectionHumanBeing.size(); i++) {
                if (collectionHumanBeing.get(i).getMinutesOfWaiting() == minutesOfWaiting) {
                    collectionHumanBeing.remove(i);
                    found = true;
                }
            }
            if (!found) {
                throw new IllegalArgumentException("Элементов со значением ВРЕМЯ ОЖИДАНИЯ = " + minutesOfWaiting + " не найдено");
            } else {
                return "Элементы со значением ВРЕМЯ ОЖИДАНИЯ = " + minutesOfWaiting + " успешно удалены";
            }
        }
    }

    public String sumOfMinutesOfWaiting() {
        if (collectionHumanBeing.isEmpty()) {
            throw new IllegalArgumentException("Коллекция пуста");
        } else {
            int sumOfMinutesOfWaiting = 0;
            for (HumanBeing humanBeing : collectionHumanBeing) {
                sumOfMinutesOfWaiting += humanBeing.getMinutesOfWaiting();
            }
            return "Сумма значений поля ВРЕМЯ ОЖИДАНИЯ для всех элементов коллекции = " + sumOfMinutesOfWaiting;
        }
    }

    public String countByImpactSpeed(double impactSpeed) {
        if (collectionHumanBeing.isEmpty()) {
            throw new IllegalArgumentException("Коллекция пуста");
        } else {
            int countByImpactSpeed = 0;
            for (HumanBeing humanBeing : collectionHumanBeing) {
                if (humanBeing.getImpactSpeed() == impactSpeed) {
                    countByImpactSpeed++;
                }
            }
            if (countByImpactSpeed == 0) {
                throw new IllegalArgumentException("Элементов со значением СКОРОСТЬ УДАРА = " + impactSpeed + " не найдено");
            } else {
                return "Количество элементов, у которых значение поля СКОРОСТЬ УДАРА = " + impactSpeed + ": " + countByImpactSpeed;
            }
        }
    }
}
