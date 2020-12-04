import java.io.*;

public class Pupils
{
    //метод выводящий на экран оценки
    public static void printMarks(Pupil p)
    {
        for(int i = 0; i<p.getSizeArrays();i++)
        {
            System.out.println(p.getMark(i));
        }
    }

    //метод выводящий на экран предметы
    public static void printSubjects(Pupil p)
    {
        for(int i = 0; i<p.getSizeArrays();i++)
        {
            System.out.println(p.getSubject(i));
        }
    }

    //метод считающий среднее арифметическое оценок
    public static  double avarage(Pupil p)
    {
        double sum =0;
        for (int i = 0; i<p.getSizeArrays();i++)
        {
            sum += p.getMark(i);
        }
        double res = sum / p.getSizeArrays();
        return res;
    }

    public static void outputPupil(Pupil v, OutputStream out) throws IOException
    {

        DataOutputStream stream  = new DataOutputStream(out);

        byte[] b = v.getSurename().getBytes();
        stream.writeInt(b.length);
        for(int i = 0; i < b.length;i++)
        {
            stream.writeByte(b[i]);
        }

        int length = v.getSizeArrays();
        stream.writeInt(length);
        for (int i = 0; i<length;i++)
        {
            b = v.getSubject(i).getBytes();
            stream.writeInt(b.length);
            for(int j = 0; j<b.length;j++)
            {
                stream.writeByte(b[j]);
            }
            stream.writeInt(v.getMark(i));
        }
    }

    public  static Pupil inputPupil(InputStream in) throws IOException, DuplicateSubjectException
    {
        DataInputStream stream = new DataInputStream(in);
        int length = stream.readInt();
        byte [] b = new byte[length];
        for(int i=0;i<length;i++)
        {
            b[i] = stream.readByte();
        }
        String surename = new String(b);

        int size = stream.readInt();
        Pupil pupil = new Schoolboy(surename,size);

        String sub;
        int mark;
        for(int i = 0; i < size; i++)
        {
            length = stream.readInt();
            b = new byte[length];
            for (int j = 0; j < length; j++)
            {
                b[j] = stream.readByte();
            }
            sub = new String(b);
            mark = stream.readInt();
            pupil.setSubject(i, sub);
            pupil.setMark(i, mark);
        }
        return pupil;
    }

    public static void writePupil(Pupil v, Writer out)
    {
        int length = v.getSizeArrays();
        PrintWriter stream = new PrintWriter(out);
        stream.println(v.getSurename());
        stream.println(length);
        for(int i = 0; i < length; i++)
        {
            stream.println(v.getSubject(i));
            stream.println(v.getMark(i));
        }
        stream.flush();
    }

    public static Pupil readPupil(Reader in) throws IOException,DuplicateSubjectException
    {
        BufferedReader stream = new BufferedReader(in);
        String surename = stream.readLine();
        int size = Integer.parseInt(stream.readLine());
        Pupil pupil = new Schoolboy(surename, size);
        String sub;
        int mark;
        for(int i = 0; i < size; i++)
        {
            sub = stream.readLine();
            mark = Integer.parseInt(stream.readLine());
            pupil.setSubject(i, sub);
            pupil.setMark(i,mark);
        }
        return pupil;
    }
}
