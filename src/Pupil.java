public interface Pupil {
    String getSurename();
    void setSurename(String surname);
    int getMark(int number);
    void setMark(int number, int newmark);
    String getSubject(int number);
    void setSubject(int number, String newsub)throws DuplicateSubjectException;
    void add(String newsub,int newmark) throws DuplicateSubjectException;
    int getSizeArrays();
}
