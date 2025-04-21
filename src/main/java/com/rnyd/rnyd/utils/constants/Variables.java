package com.rnyd.rnyd.utils.constants;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

public class Variables {
     public final static SecretKey SECRET_KEY_GEN = Keys.secretKeyFor(SignatureAlgorithm.HS256);
     public final static long EXPIRATION_TIME = 86400000; // 1 dia en milisegundos
     public final static String USER_EMAIL_DOES_NOT_EXISTS = "This email does not exist.";
     public final static String USER_EMAIL_ALREADY_EXISTS = "This email already exists, try logging in.";

     public final static String CURRENCY = "eur";

     // DIETS
     public final static String DIET_CREATED = "Diet was created successfully.";
     public final static String DIET_ALREADY_EXISTS = "Error while creating Diet.";
     public final static String DIET_UPDATED = "Diet was updated successfully.";
     public final static String DIET_NOT_UPDATED = "Error while updating Diet.";
     public final static String DIET_DELETED = "Diet was deleted successfully.";
     public final static String DIET_NOT_DELETED = "Error while deleting Diet.";
     public final static String DIET_ASSIGNED = "Diet was assigned successfully.";
     public final static String DIET_NOT_ASSIGNED = "Error while assigning Diet.";
     public final static String DIET_NOT_FOUND = "Diet was not found.";

     // USERS
     public final static String USER_DELETED = "User was deleted successfully.";
     public final static String USER_UPDATED = "User was updated successfully.";

     // USER PROGRESS
     public final static String OVERWROTE_PROGRESS = "%s progress was overwritten successfully.";
     public final static String PROGRESS_UPLOADED = "User progress was uploaded successfully.";


     // WORKOUTS
     public final static String WORKOUT_CREATED = "Workout was created successfully.";
     public final static String WORKOUT_ALREADY_EXISTS = "Error while creating Workout.";
     public final static String WORKOUT_UPDATED = "Workout was updated successfully.";
     public final static String WORKOUT_NOT_UPDATED = "Error while updating Workout.";
     public final static String WORKOUT_DELETED = "Workout was deleted successfully.";
     public final static String WORKOUT_NOT_DELETED = "Error while deleting Workout.";
     public final static String WORKOUT_ASSIGNED = "Workout was assigned successfully.";
     public final static String WORKOUT_NOT_ASSIGNED = "Error while assigning Workout.";

     // PLANS
     public final static String PLAN_ASSIGNED = "The new assigned plan is: %s.";

     // SIGN-IN
     public final static String WRONG_LOGIN = "The email or password is incorrect.";
     public final static String VALID_TOKEN = "The token is valid.";
     public final static String INVALID_TOKEN = "The token is NOT valid.";

     // STRIPE
     public final static String SUBSCRIPTION_CREATED = "Subscription created successfully with Id: %s";
     public final static String SUBSCRIPTION_NOT_CREATED = "Subscription NOT created.";

     // ERR
     public final static String ERROR_PDF = "Error al leer el PDF.";



}