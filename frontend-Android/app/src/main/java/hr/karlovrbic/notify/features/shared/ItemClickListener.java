package hr.karlovrbic.notify.features.shared;

/**
 * Created by thekarlo95 on 21.01.17..
 */
public interface ItemClickListener<T> {
    void onClick(T user, int adapterPosition);
}
