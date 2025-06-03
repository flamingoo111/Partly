package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import kotlinx.coroutines.Job;

public class VacationAdapter extends ArrayAdapter<JobForm> {

    interface getSavedVacancy {
        void getSavedVacncybylogin();
        void changeLiked(int id);
        void getAcceptVacancy();
        void changeAccept(int id);
    }

    private final getSavedVacancy contextSavedVacancy;

    public VacationAdapter(Context context, JobForm[] arr) {
        super(context, R.layout.adapter_item, arr);
        contextSavedVacancy = (getSavedVacancy) context;
    }

    static class ViewHolder {
        TextView users;
        TextView name;
        TextView place;
        TextView requirements;
        TextView price;
        ImageButton saved;
        Button click_btn;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        final JobForm vacation = getItem(position);

        // Инициализация ViewHolder
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_item, parent, false);
            holder = new ViewHolder();
            holder.users = convertView.findViewById(R.id.users);
            holder.name = convertView.findViewById(R.id.name);
            holder.place = convertView.findViewById(R.id.place);
            holder.requirements = convertView.findViewById(R.id.requirements);
            holder.price = convertView.findViewById(R.id.price);
            holder.saved = convertView.findViewById(R.id.heart_icon);
            holder.click_btn = convertView.findViewById(R.id.click_btn);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Заполняем адаптер
        holder.users.setText("Создатель объявления: " + vacation.user);
        holder.name.setText(String.valueOf(vacation.name));
        holder.place.setText(String.valueOf(vacation.place));
        holder.requirements.setText(String.valueOf(vacation.shortDescription));
        holder.price.setText("Оплата: " + vacation.price + " ₽");

        // Устанавливаем цвет кнопки в зависимости от состояния isLiked
        holder.saved.setColorFilter(vacation.isLiked ? Color.parseColor("#cc9e21") : Color.TRANSPARENT);
        holder.click_btn.setBackgroundColor(vacation.isAccept ? Color.parseColor("#00BFA5") :Color.parseColor("#4CAF50"));
        holder.click_btn.setText(vacation.isAccept ? ("Номер телефона вакансии: " + vacation.number) : "Откликнуться");
//        holder.click_btn.setOnClickListener(v -> {
//        });
        holder.click_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vacation.isAccept = true;
                holder.click_btn.setBackgroundColor(Color.parseColor("#00BFA5"));
                holder.click_btn.setText("Номер телефона вакансии: " + vacation.number);
                // Уведомляем адаптер об изменении данных
                notifyDataSetChanged();
                contextSavedVacancy.changeAccept(vacation.id);
                // Вызываем метод для обработки сохранения
                System.out.println("CLICK!!!");
            }
        });
        // Обработчик клика
        holder.saved.setOnClickListener(v -> {
            // Переключаем состояние isLiked
            vacation.isLiked = !vacation.isLiked;
            // Обновляем цвет кнопки
            holder.saved.setColorFilter(vacation.isLiked ? Color.parseColor("#cc9e21") : Color.TRANSPARENT);
            // Уведомляем адаптер об изменении данных
            notifyDataSetChanged();
            // Вызываем метод для обработки сохранения
            contextSavedVacancy.changeLiked(vacation.id);
            System.out.println("Clicked: " + vacation.id + ", isLiked: " + vacation.isLiked);
        });

        return convertView;
    }
}