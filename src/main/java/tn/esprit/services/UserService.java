package tn.esprit.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.entities.StaticOfUser;
import tn.esprit.entities.User;
import tn.esprit.repositories.UserRepository;
import tn.esprit.repositories.UserStaticRepository;

@Service
@Transactional
@Slf4j
public class UserService implements IuserService {

	private final UserRepository userRepository;

	private final UserStaticRepository userstaticRepo;

	public UserService(UserRepository userRepository, UserStaticRepository userstaticRepo) {
		this.userRepository = userRepository;
		this.userstaticRepo = userstaticRepo;
	}

	@Override
	public Date weekDate(Date date) {
		// TODO Auto-generated method stub
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, -7);

		return calendar.getTime();
	}

	@Override
	public int userAddinWeek() throws ParseException {
		// TODO Auto-generated method stub
		Date date = new Date();
		SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateNow = date.toString();
		String dateWeek = weekDate(date).toString();
		// log.info(userRepository.UserStatistic(DateFor.parse(dateNow),
		// DateFor.parse(dateWeek) ));

		return userRepository.UserStatistic(date, weekDate(date));
	}

	@Override
	public StaticOfUser addUserStatic() throws ParseException {
		// TODO Auto-generated method stub
		StaticOfUser ofUser = new StaticOfUser();
		Date datetoday = new Date();
		ofUser.setDayofweekLater(weekDate(datetoday));
		ofUser.setDateDay(datetoday);
		ofUser.setNumberOfuser(userAddinWeek());
		// userstaticRepo.save(ofUser);

		return ofUser;
	}

}
