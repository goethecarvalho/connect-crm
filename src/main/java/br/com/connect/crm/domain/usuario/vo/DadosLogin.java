package br.com.connect.crm.domain.usuario.vo;

public record DadosLogin() {
    static String username;
    static String password;

    // Getters e setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
