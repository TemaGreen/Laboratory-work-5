import java.io.Serializable;
import java.util.Arrays;
public class Schoolboy implements  Pupil, Serializable
{
    private String surname;
    private Register[] registers;

    //метод возвращающий фамилию
    public String getSurename()
    {
        return surname;
    }

    //метод модифицирующий фамилию
    public void setSurename(String n)
    {
        surname = n;
    }

    //метод возращающий i-ю оценку
    public int getMark(int i) {
        return registers[i].mark;
    }

    //метод модифицирующий i-ю оценку
    public void setMark(int i, int a) {
        if ((a < 2) || (a > 5)) {
            throw new MarkOutOfBoundsException("Выход за границу");
        }
        registers[i].mark = a;
    }

    //метод возвращающий i-й предмет
    public String getSubject(int i)
    {
        return registers[i].subject;
    }

    //метод модифицирующий i-й предмет
    public void setSubject(int i, String s) throws DuplicateSubjectException
    {
        for(Register register : registers)
        {
            if (register.subject.equals(s))
            {
                throw new DuplicateSubjectException("Такой предмет уже существует");
            }
        }
        registers[i].subject = s;
    }

    // метод добавления оценки и предмета
    public void add(String s, int m) throws DuplicateSubjectException {
        for (Register register : registers) {
            if (register != null) {
                if (register.getSubject().equals(s)) {
                    throw new DuplicateSubjectException("Такой предмет уже существует");
                }
            }
        }
        if ((m < 2) || (m > 5)) {
            throw new MarkOutOfBoundsException("Выход за границу");
        }
        registers = Arrays.copyOf(registers, registers.length + 1);
        Register a = new Register(m, s);
        registers[registers.length - 1] = a;
    }

    //метод возращающий размер массивов
    public int getSizeArrays()
    {
        return  registers.length;
    }

    //конструктор
    public Schoolboy(String n, int size)
    {
        surname = n;
        registers = new Register[size];
        for (int j = 0; j < size; j++) {
            registers[j] = new Register();
        }
    }

    public String toString()
    {
        StringBuffer res = new StringBuffer();
        res.append(surname + "\n");
        for (Register i : registers)
        {
            res.append(i.getSubject() + " ");
            res.append(i.getMark() + "\n");
        }
        return res.toString();
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
        {
            return true;
        }
        if (obj instanceof Schoolboy)
        {
            Schoolboy boy = (Schoolboy) obj;
            if(!boy.getSurename().equals(getSurename()))
            {
                return false;
            }
            else if(boy.getSizeArrays() != getSizeArrays())
            {
                return false;
            }
            for (int i = 0; i < getSizeArrays(); i++)
            {
                if((!boy.registers[i].equals(registers[i])))
                {
                    return false;
                }
            }
            return true;
        }
        return  false;
    }

    private class Register implements Serializable
    {
        private int mark; //оценкa
        private String subject;//предмет

        //метод возращающий оценку
        public int getMark() {
            return mark;
        }

        //метод модифицирующий оценку
        public void setMark(int a) {
            if ((a < 2) || (a > 5)) {
                throw new MarkOutOfBoundsException("Выход за границу");
            }
            mark = a;
        }

        //метод возвращающий предмет
        public String getSubject() {
            return subject;
        }

        //метод модифицирующий предмет
        public void setSubject(String s) {
            subject = s;
        }

       //конструктор
        public Register(int m, String s)
        {
            mark = m;
            subject = s;
        }

        //пустой конструктор
        public Register() {
            mark = 0;
            subject = "";
        }
    }
}