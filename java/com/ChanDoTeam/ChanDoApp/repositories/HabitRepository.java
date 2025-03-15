package com.ChanDoTeam.ChanDoApp.repositories;

import com.ChanDoTeam.ChanDoApp.models.Habit;
<<<<<<< HEAD
import com.ChanDoTeam.ChanDoApp.models.User;
=======
>>>>>>> b80cf8db0ed71f2ad1f64892d866a61570165012
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Long> {

    // Найти все привычки по userId
    List<Habit> findByUserId(Long userId);

<<<<<<< HEAD

    List<Habit> findByUser(User user);


=======
>>>>>>> b80cf8db0ed71f2ad1f64892d866a61570165012
    // Найти привычки по userId и категории
    List<Habit> findByUserIdAndCategory(Long userId, String category);

    // Проверить, существует ли привычка с таким названием и userId
    boolean existsByTitleAndUserId(String title, Long userId);
}