package by.epam.university.service.impl;

import by.epam.university.dao.DAOFactory;
import by.epam.university.dao.DAOManager;
import by.epam.university.dao.UserDAO;
import by.epam.university.dao.exception.DAOException;
import by.epam.university.model.User;
import by.epam.university.service.UserService;
import by.epam.university.service.exception.ServiceException;
import by.epam.university.service.exception.ValidationException;
import by.epam.university.service.util.HashUtil;

/**
 * It is a class for working with user operations.
 */
public class UserServiceImpl implements UserService {

    private static DAOManager daoManager = DAOFactory.getFactory().getNonTransactionalDAOManager();

    private static UserDAO userDAO = daoManager.getUserDAO();
    // private static final BasketDAO basketDAO = daoManager.getBasketDAO();

    /**
     * Registrates specified user. Password before sending to DAO level is hashed
     * according specified algorithms.
     *
     * @param user
     *            the user
     * @return the id of registrated user
     * @throws ValidationException
     *             the exception during validation of user parameters or if such
     *             email or login is already exists.
     * @throws ServiceException
     *             the service exception
     */
    @Override
    public Integer registrate(User user) throws ValidationException, ServiceException {
        /*if ( ! Validator.isRegistrationDataValid(user)) {
            throw new ValidationException("Sent data isn't valid.");
        }
*/
        try {
            if (userDAO.isUserExists(user.getLogin(), user.getPassword())) {
                throw new ValidationException("Email or login are not unique.");
            }
            String hashedPassword = HashUtil.toHash(user.getPassword());
            user.setPassword(hashedPassword);
            return userDAO.addUser(user);
        } catch (DAOException e) {
            throw new ServiceException("Exception during user registration.", e);
        }
    }

    /**
     * Returns the {@link User} who appropriates specified {@code login or email}
     * and {@code password}. Returns {@code null} if such user is not found.
     * Password before sending to DAO level is hashed according specified
     * algorithms.
     *
     * @param login
     *            the user's login
     * @param password
     *            the password
     * @return the user who appropriates specified {@code login or email} and
     *         {@code password}. Returns {@code null} if such user is not found.
     * @throws ValidationException
     *             the exception during validation login or email and password
     * @throws ServiceException
     *             the service exception
     */
    @Override
    public User logIn(String login, String password) throws ValidationException, ServiceException {
        /*if ( ! Validator.isLoginDataValid(loginOrEmail, password)) {
            throw new ValidationException("Sent data isn't valid.");
        } */

        try {
            String hashedPassword = HashUtil.toHash(password);
            return userDAO.getUser(login, hashedPassword);
        } catch (DAOException e) {
            throw new ServiceException("Exception during user signing in.", e);
        }
    }

    /**
     * Checks if the user with specified {@code idUser} has filled full info about
     * yourself by checking of filling the name. If the name is known returns
     * {@code true}, if not {@code false}.
     *
     * @param idUser
     *            the id of user
     * @return {@code true}, if user has filled full info
     * @throws ServiceException
     *             the service exception
     */
    @Override
    public boolean isUserFillFullInfo(Integer idUser) throws ServiceException {
        try {
            String name = userDAO.getUserName(idUser);
            return name != null;
        } catch (DAOException e) {
            throw new ServiceException(
                    "Exception during checking filling name"
                            + "by user as criteria of filling full info.", e);
        }
    }

    /**
     * Sets the user full info.
     *
     * @param user
     *            the user object containing full info about user (in addition to
     *            existing already login, email and password)
     * @throws ValidationException
     *             the exception during validation of user parameters
     * @throws ServiceException
     *             the service exception
     */
    @Override
    public void setUserFullInfo(User user) throws ValidationException, ServiceException {
        /*if ( ! Validator.isUserDataValid(user)) {
            throw new ValidationException("Sent data isn't valid.");
        } */

        try {
            userDAO.setUserFullInfo(user);
        } catch (DAOException e) {
            throw new ServiceException(
                    "Exception during setting full info about user.", e);
        }
    }
}