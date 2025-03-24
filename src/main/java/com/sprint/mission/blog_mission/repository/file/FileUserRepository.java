package com.sprint.mission.blog_mission.repository.file;

import com.sprint.mission.blog_mission.entity.User;
import com.sprint.mission.blog_mission.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.Optional;

@Repository
public class FileUserRepository implements UserRepository {
    private static final String FILE_PATH = "users.ser";

    @Override
    public void save(User user) {
        if (user.getId() == null) {
            throw new IllegalArgumentException("ID가 공백입니다.");
        }

        if (existsById(user.getId())) {
            throw new IllegalArgumentException("이미 존재하는 ID입니다: " + user.getId());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            String userData = user.getId() + "," + user.getPassword() + "," + user.getNickname() + "," + user.getEmail();
            writer.write(userData);
            writer.newLine();
            System.out.println("User 저장 완료: " + userData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> findById(String id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0 && data[0].equals(id)) {
                    User user = new User(data[0], data[1], data[2], data[3]);
                    return Optional.of(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public boolean existsById(String id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0 && data[0].equals(id)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
