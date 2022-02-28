package Entity;


public class BankBranch {
    private String nameBank;
    private String codeBranch;
    private String bossFullName;
    private String nationalId;
    private String password;

    public BankBranch(){}

    public BankBranch(String nameBank, String codeBranch, String bossFullName, String nationalId, String password) {
        this.nameBank = nameBank;
        this.codeBranch = codeBranch;
        this.bossFullName = bossFullName;
        this.nationalId = nationalId;
        this.password = password;
    }

    public String getNameBank() {
        return nameBank;
    }

    public String getCodeBranch() {
        return codeBranch;
    }

    public String getBossFullName() {
        return bossFullName;
    }

    public String getNationalId() {
        return nationalId;
    }

    public String getPassword() {
        return password;
    }
}
