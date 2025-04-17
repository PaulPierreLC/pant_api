package group.pant.api.service;

import group.pant.api.model.*;
import group.pant.api.repository.RestaurantRepository;
import group.pant.api.repository.UtilisateurRepository;
import group.pant.api.repository.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Map;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final RestaurantRepository restaurantRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Integer id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation with id " + id + " not found"));
    }

    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(Integer id, Reservation reservation) {
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation with id " + id + " not found"));

        existingReservation.setIdUtilisateur(reservation.getIdUtilisateur());
        existingReservation.setIdRestaurant(reservation.getIdRestaurant());
        existingReservation.setHeure(reservation.getHeure());
        existingReservation.setCouverts(reservation.getCouverts());

        return reservationRepository.save(existingReservation);
    }

    public void deleteReservation(Integer id) {
        reservationRepository.deleteById(id);
    }

    public Reservation patchReservation(Integer id, Map<String, Object> patch) {
        Reservation existinReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation with id " + id + " not found"));

        patch.forEach((key, value) -> {
            switch (key) {
                case "idUtilisateur":
                    if (value instanceof Map<?, ?> utilisateurMap) {
                        Integer utilisateurId = (Integer) utilisateurMap.get("id");
                        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                                .orElseThrow(() -> new EntityNotFoundException("Utilisateur with id " + utilisateurId + " not found"));
                        existinReservation.setIdUtilisateur(utilisateur);
                    }
                    break;
                case "idRestaurant":
                    if (value instanceof Map<?, ?> restaurantMap) {
                        Integer restaurantId = (Integer) restaurantMap.get("id");
                        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                                .orElseThrow(() -> new EntityNotFoundException("Restaurant with id " + restaurantId + " not found"));
                        existinReservation.setIdRestaurant(restaurant);
                    }
                    break;
                case "heure":
                    String heureString = (String) value;
                    Instant heureInstant = Instant.parse(heureString);
                    existinReservation.setHeure(heureInstant);
                    break;
                case "couverts":
                    existinReservation.setCouverts((Integer) value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        return reservationRepository.save(existinReservation);
    }
}
