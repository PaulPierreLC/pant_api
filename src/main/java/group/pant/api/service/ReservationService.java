package group.pant.api.service;

import group.pant.api.model.Reservation;
import group.pant.api.model.Restaurant;
import group.pant.api.model.Utilisateur;
import group.pant.api.repository.RestaurantRepository;
import group.pant.api.repository.UtilisateurRepository;
import group.pant.api.repository.ReservationRepository;
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

    // Récupérer toutes les réservations
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    // Récupérer une réservation par son ID
    public Reservation getReservationById(Integer id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
    }

    // Créer une nouvelle réservation
    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    // Mettre à jour une réservation
    public Reservation updateReservation(Integer id, Reservation reservationDetails) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        reservation.setIdUtilisateur(reservationDetails.getIdUtilisateur());
        reservation.setIdRestaurant(reservationDetails.getIdRestaurant());
        reservation.setHeure(reservationDetails.getHeure());
        reservation.setCouverts(reservationDetails.getCouverts());
        return reservationRepository.save(reservation);
    }

    // Supprimer une réservation
    public void deleteReservation(Integer id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        reservationRepository.delete(reservation);
    }

    // Mettre à jour partiellement une réservation
    public Reservation patchReservation(Integer id, Map<String, Object> updates) {
        // Find the reservation by its ID
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        updates.forEach((key, value) -> {
            if ("id_utilisateur".equals(key)) {
                // Find the Utilisateur by ID and set it on the reservation
                Utilisateur utilisateur = utilisateurRepository.findById((Integer) value)
                        .orElseThrow(() -> new RuntimeException("Utilisateur not found"));
                reservation.setIdUtilisateur(utilisateur);
            } else if ("id_restaurant".equals(key)) {
                // Find the Restaurant by ID and set it on the reservation
                Restaurant restaurant = restaurantRepository.findById((Integer) value)
                        .orElseThrow(() -> new RuntimeException("Restaurant not found"));
                reservation.setIdRestaurant(restaurant);
            } else if ("heure".equals(key)) {
                // Convert String to Instant (ISO 8601 format, e.g., "2025-01-07T12:34:56Z")
                String heureString = (String) value;
                Instant heureInstant = Instant.parse(heureString);  // Converts String to Instant
                reservation.setHeure(heureInstant);
            } else if ("couverts".equals(key)) {
                reservation.setCouverts((Integer) value);
            }
        });

        return reservationRepository.save(reservation);
    }
}
