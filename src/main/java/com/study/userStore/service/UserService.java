package com.study.userStore.service;

import com.study.userStore.dao.FileDao;
import com.study.userStore.dao.User;
import com.study.userStore.dao.UserDao;
import com.study.userStore.dao.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void initialize() {
        UserRepository userRepository = UserRepository.getInstance();
        userRepository.clear();
        userRepository.setList(userDao.getAll());
    }

    public List<User> getAll() {
        return UserRepository.getInstance().getAllUsers();
    }

    public void addToRepository(User user) {
        UserRepository.getInstance().addNewUsers(user);
    }

    public void save() {
        List<User> allUsers = UserRepository.getInstance().getAllUsers();
        List<User> userToSave = new ArrayList<>();

        for (User user : allUsers) {
            if (user.getId() == null) {
                userToSave.add(user);
            }
        }
        userDao.saveToDB(userToSave);
//        initialize();
    }

    public void saveToFile() {
        List<User> userList = userDao.getAll();
        FileDao fileDao = new FileDao();
        fileDao.saveToFile(userList);
    }

    public void deleteUser(String id, String name) {
        if (UserRepository.getInstance().deleteUser(id, name)) {
            userDao.deleteFromDB(id);
        }
    }
}
