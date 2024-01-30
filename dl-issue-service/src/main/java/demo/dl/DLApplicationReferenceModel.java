package demo.dl;


import jakarta.persistence.*;

@Entity
@Table(name = "dlno_applicationid")
public class DLApplicationReferenceModel {
    
    @Id
    private String applicationid;
   
    private String dlno;
    

    
    public DLApplicationReferenceModel(String applicationid, String dlno) {
        this.dlno = dlno;
        this.applicationid = applicationid;
    }

    public String getApplicationid() {
        return applicationid;
    }

    public void setApplicationid(String applicationid) {
        this.applicationid = applicationid;
    }

    
    public String getDlno() {
        return dlno;
    }

    public void setDlno(String dlno) {
        this.dlno = dlno;
    }

    

    public DLApplicationReferenceModel() {
    }

    

    

}
