package sample.common.service;

public interface LoginService {

    boolean login(String userId, String password);

    boolean signup(String userId, String password);
}