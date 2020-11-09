package com.example.movietime.ui.login;

import com.example.movietime.assemblers.DatabaseAssembler;
import com.example.movietime.interfaces.Database;
import com.example.movietime.interfaces.LoginView;
import com.example.movietime.model.User;

public class LoginViewModel {
    private Database database;
    private LoginView view;
    public LoginViewModel(LoginView view) {
        database = DatabaseAssembler.getInstance();
        this.view = view;
    }
    public void login(String username, String passowrdString) {
        User user = database.getUser(username);
        if (user == null || !user.getPassword().equals(passowrdString)) {
            view.tryAgainToast();
            return;
        }
        view.startMovieListActivity(user.getAdmin());
    }
    public void register(String username, String passowrd) {
        User user = new User();
        user.setPassword(passowrd);
        user.setUsername(username);
        database.addUser(user);
        view.RegistrationSucess();
        view.startMovieListActivity(false);
    }
}
