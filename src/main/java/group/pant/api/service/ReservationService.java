package group.pant.api.service;

import group.pant.api.model.Reservation;
import group.pant.api.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    // Injection par constructeur
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    // Récupérer toutes les réservations
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    // Récupérer une réservation par son ID
    public Optional<Reservation> getReservationById(Integer id) {
        return reservationRepository.findById(id);
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
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        updates.forEach((key, value) -> {
            if ("id_utilisateur".equals(key)) {
                reservation.setIdUtilisateur((Integer) value);
            } else if ("id_restaurant".equals(key)) {
                reservation.setIdRestaurant((Integer) value);
            } else if ("heure".equals(key)) {
                reservation.setHeure((String) value); // Assurez-vous que la valeur est une chaîne date valide
            } else if ("couverts".equals(key)) {
                reservation.setCouverts((Integer) value);
            }
        });

        return reservationRepository.save(reservation);
    }
}
