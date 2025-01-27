package com.example.esc_walker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SearchCity extends AppCompatActivity {

    ImageButton ibt_date;
    private Button search_btn_lookup;
    private ImageButton ibt_bus;
    private ImageButton ibt_train;
    private ImageButton ibt_airplane;
    ArrayAdapter<CharSequence> adsp_start_city, adsp_arrive_city,
            adsp_start_tm, adsp_arrive_tm;
    String start_tm;
    String arrive_tm;

    private ImageButton search_ibtn_back;
    Calendar calendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener datePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH,month);
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            updateLabel();
        }
    };

//    private FragmentPagerAdapter fragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final Spinner sp_start_city = (Spinner)findViewById(R.id.sp_start_city);
        final Spinner sp_arrive_city = (Spinner)findViewById(R.id.sp_arrive_city);
        final Spinner sp_start_tm = (Spinner)findViewById(R.id.sp_start_terminal);
        final Spinner sp_arrive_tm = (Spinner)findViewById(R.id.sp_arrive_terminal);

        ibt_bus = findViewById(R.id.ibt_bus);
        ibt_train = findViewById(R.id.ibt_train);
        ibt_airplane = findViewById(R.id.ibt_airplane);

        ibt_bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adsp_start_city = ArrayAdapter.createFromResource(SearchCity.this,R.array.spinner_city_bus, android.R.layout.simple_spinner_dropdown_item);
                adsp_start_city.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp_start_city.setAdapter(adsp_start_city);
                sp_start_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if(adsp_start_city.getItem(position).equals("서울")){
                            adsp_start_tm = ArrayAdapter.createFromResource(SearchCity.this,R.array.spinner_tm_seoul_bus, android.R.layout.simple_spinner_dropdown_item);
                        }else if(adsp_start_city.getItem(position).equals("인천")) {
                            adsp_start_tm = ArrayAdapter.createFromResource(SearchCity.this, R.array.spinner_tm_incheon_bus, android.R.layout.simple_spinner_dropdown_item);
                        }else if(adsp_start_city.getItem(position).equals("대구")) {
                            adsp_start_tm = ArrayAdapter.createFromResource(SearchCity.this, R.array.spinner_tm_daegu_bus, android.R.layout.simple_spinner_dropdown_item);
                        }else if(adsp_start_city.getItem(position).equals("대전")) {
                            adsp_start_tm = ArrayAdapter.createFromResource(SearchCity.this, R.array.spinner_tm_daejeon_bus, android.R.layout.simple_spinner_dropdown_item);
                        }else if(adsp_start_city.getItem(position).equals("부산")) {
                            adsp_start_tm = ArrayAdapter.createFromResource(SearchCity.this, R.array.spinner_tm_busan_bus, android.R.layout.simple_spinner_dropdown_item);
                        }else if(adsp_start_city.getItem(position).equals("울산")) {
                            adsp_start_tm = ArrayAdapter.createFromResource(SearchCity.this, R.array.spinner_tm_ulsan_bus, android.R.layout.simple_spinner_dropdown_item);
                        }else if(adsp_start_city.getItem(position).equals("광주")) {
                            adsp_start_tm = ArrayAdapter.createFromResource(SearchCity.this, R.array.spinner_tm_gwangju_bus, android.R.layout.simple_spinner_dropdown_item);
                        }
                        adsp_start_tm.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sp_start_tm.setAdapter(adsp_start_tm);
                        sp_start_tm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                start_tm = adsp_start_tm.getItem(position).toString();
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

                adsp_arrive_city = ArrayAdapter.createFromResource(SearchCity.this,R.array.spinner_city_bus, android.R.layout.simple_spinner_dropdown_item);
                adsp_arrive_city.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp_arrive_city.setAdapter(adsp_arrive_city);
                sp_arrive_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if(adsp_arrive_city.getItem(position).equals("서울")){
                            adsp_arrive_tm = ArrayAdapter.createFromResource(SearchCity.this,R.array.spinner_tm_seoul_bus, android.R.layout.simple_spinner_dropdown_item);
                        }else if(adsp_arrive_city.getItem(position).equals("인천")) {
                            adsp_arrive_tm = ArrayAdapter.createFromResource(SearchCity.this, R.array.spinner_tm_incheon_bus, android.R.layout.simple_spinner_dropdown_item);
                        }else if(adsp_arrive_city.getItem(position).equals("대구")) {
                            adsp_arrive_tm = ArrayAdapter.createFromResource(SearchCity.this, R.array.spinner_tm_daegu_bus, android.R.layout.simple_spinner_dropdown_item);
                        }else if(adsp_arrive_city.getItem(position).equals("대전")) {
                            adsp_arrive_tm = ArrayAdapter.createFromResource(SearchCity.this, R.array.spinner_tm_daejeon_bus, android.R.layout.simple_spinner_dropdown_item);
                        }else if(adsp_arrive_city.getItem(position).equals("부산")) {
                            adsp_arrive_tm = ArrayAdapter.createFromResource(SearchCity.this, R.array.spinner_tm_busan_bus, android.R.layout.simple_spinner_dropdown_item);
                        }else if(adsp_arrive_city.getItem(position).equals("울산")) {
                            adsp_arrive_tm = ArrayAdapter.createFromResource(SearchCity.this, R.array.spinner_tm_ulsan_bus, android.R.layout.simple_spinner_dropdown_item);
                        }else if(adsp_arrive_city.getItem(position).equals("광주")) {
                            adsp_arrive_tm = ArrayAdapter.createFromResource(SearchCity.this, R.array.spinner_tm_gwangju_bus, android.R.layout.simple_spinner_dropdown_item);
                        }
                        adsp_arrive_tm.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sp_arrive_tm.setAdapter(adsp_arrive_tm);
                        sp_arrive_tm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                arrive_tm = adsp_arrive_tm.getItem(position).toString();
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }
        });

        ibt_train.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adsp_start_city = ArrayAdapter.createFromResource(SearchCity.this,R.array.spinner_city_train, android.R.layout.simple_spinner_dropdown_item);
                adsp_start_city.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp_start_city.setAdapter(adsp_start_city);
                sp_start_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if(adsp_start_city.getItem(position).equals("서울")){
                            adsp_start_tm = ArrayAdapter.createFromResource(SearchCity.this,R.array.spinner_tm_seoul_train, android.R.layout.simple_spinner_dropdown_item);
                        }else if(adsp_start_city.getItem(position).equals("인천")) {
                            adsp_start_tm = ArrayAdapter.createFromResource(SearchCity.this, R.array.spinner_tm_incheon_train, android.R.layout.simple_spinner_dropdown_item);
                        }else if(adsp_start_city.getItem(position).equals("대구")) {
                            adsp_start_tm = ArrayAdapter.createFromResource(SearchCity.this, R.array.spinner_tm_daegu_train, android.R.layout.simple_spinner_dropdown_item);
                        }else if(adsp_start_city.getItem(position).equals("대전")) {
                            adsp_start_tm = ArrayAdapter.createFromResource(SearchCity.this, R.array.spinner_tm_daejeon_train, android.R.layout.simple_spinner_dropdown_item);
                        }else if(adsp_start_city.getItem(position).equals("부산")) {
                            adsp_start_tm = ArrayAdapter.createFromResource(SearchCity.this, R.array.spinner_tm_busan_train, android.R.layout.simple_spinner_dropdown_item);
                        }else if(adsp_start_city.getItem(position).equals("울산")) {
                            adsp_start_tm = ArrayAdapter.createFromResource(SearchCity.this, R.array.spinner_tm_ulsan_train, android.R.layout.simple_spinner_dropdown_item);
                        }else if(adsp_start_city.getItem(position).equals("광주")) {
                            adsp_start_tm = ArrayAdapter.createFromResource(SearchCity.this, R.array.spinner_tm_gwangju_train, android.R.layout.simple_spinner_dropdown_item);
                        }
                        adsp_start_tm.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sp_start_tm.setAdapter(adsp_start_tm);
                        sp_start_tm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                start_tm = adsp_start_tm.getItem(position).toString();
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

                adsp_arrive_city = ArrayAdapter.createFromResource(SearchCity.this,R.array.spinner_city_train, android.R.layout.simple_spinner_dropdown_item);
                adsp_arrive_city.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp_arrive_city.setAdapter(adsp_arrive_city);
                sp_arrive_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if(adsp_arrive_city.getItem(position).equals("서울")){
                            adsp_arrive_tm = ArrayAdapter.createFromResource(SearchCity.this,R.array.spinner_tm_seoul_train, android.R.layout.simple_spinner_dropdown_item);
                        }else if(adsp_arrive_city.getItem(position).equals("인천")) {
                            adsp_arrive_tm = ArrayAdapter.createFromResource(SearchCity.this, R.array.spinner_tm_incheon_train, android.R.layout.simple_spinner_dropdown_item);
                        }else if(adsp_arrive_city.getItem(position).equals("대구")) {
                            adsp_arrive_tm = ArrayAdapter.createFromResource(SearchCity.this, R.array.spinner_tm_daegu_train, android.R.layout.simple_spinner_dropdown_item);
                        }else if(adsp_arrive_city.getItem(position).equals("대전")) {
                            adsp_arrive_tm = ArrayAdapter.createFromResource(SearchCity.this, R.array.spinner_tm_daejeon_train, android.R.layout.simple_spinner_dropdown_item);
                        }else if(adsp_arrive_city.getItem(position).equals("부산")) {
                            adsp_arrive_tm = ArrayAdapter.createFromResource(SearchCity.this, R.array.spinner_tm_busan_train, android.R.layout.simple_spinner_dropdown_item);
                        }else if(adsp_arrive_city.getItem(position).equals("울산")) {
                            adsp_arrive_tm = ArrayAdapter.createFromResource(SearchCity.this, R.array.spinner_tm_ulsan_train, android.R.layout.simple_spinner_dropdown_item);
                        }else if(adsp_arrive_city.getItem(position).equals("광주")) {
                            adsp_arrive_tm = ArrayAdapter.createFromResource(SearchCity.this, R.array.spinner_tm_gwangju_train, android.R.layout.simple_spinner_dropdown_item);
                        }
                        adsp_arrive_tm.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sp_arrive_tm.setAdapter(adsp_arrive_tm);
                        sp_arrive_tm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                arrive_tm = adsp_arrive_tm.getItem(position).toString();
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }
        });

        ibt_airplane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adsp_start_city = ArrayAdapter.createFromResource(SearchCity.this,R.array.spinner_city_airplane, android.R.layout.simple_spinner_dropdown_item);
                adsp_start_city.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp_start_city.setAdapter(adsp_start_city);
                sp_start_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if(adsp_start_city.getItem(position).equals("서울")){
                            adsp_start_tm = ArrayAdapter.createFromResource(SearchCity.this,R.array.spinner_tm_seoul_airplane, android.R.layout.simple_spinner_dropdown_item);
                        }else if(adsp_start_city.getItem(position).equals("인천")) {
                            adsp_start_tm = ArrayAdapter.createFromResource(SearchCity.this, R.array.spinner_tm_incheon_airplane, android.R.layout.simple_spinner_dropdown_item);
                        }else if(adsp_start_city.getItem(position).equals("대구")) {
                            adsp_start_tm = ArrayAdapter.createFromResource(SearchCity.this, R.array.spinner_tm_daegu_airplane, android.R.layout.simple_spinner_dropdown_item);
                        }else if(adsp_start_city.getItem(position).equals("제주")) {
                            adsp_start_tm = ArrayAdapter.createFromResource(SearchCity.this, R.array.spinner_tm_jeju_airplane, android.R.layout.simple_spinner_dropdown_item);
                        }else if(adsp_start_city.getItem(position).equals("부산")) {
                            adsp_start_tm = ArrayAdapter.createFromResource(SearchCity.this, R.array.spinner_tm_busan_airplane, android.R.layout.simple_spinner_dropdown_item);
                        }else if(adsp_start_city.getItem(position).equals("울산")) {
                            adsp_start_tm = ArrayAdapter.createFromResource(SearchCity.this, R.array.spinner_tm_ulsan_airplane, android.R.layout.simple_spinner_dropdown_item);
                        }else if(adsp_start_city.getItem(position).equals("광주")) {
                            adsp_start_tm = ArrayAdapter.createFromResource(SearchCity.this, R.array.spinner_tm_gwangju_airplane, android.R.layout.simple_spinner_dropdown_item);
                        }
                        adsp_start_tm.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sp_start_tm.setAdapter(adsp_start_tm);
                        sp_start_tm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                start_tm = adsp_start_tm.getItem(position).toString();
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

                adsp_arrive_city = ArrayAdapter.createFromResource(SearchCity.this,R.array.spinner_city_airplane, android.R.layout.simple_spinner_dropdown_item);
                adsp_arrive_city.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp_arrive_city.setAdapter(adsp_arrive_city);
                sp_arrive_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if(adsp_arrive_city.getItem(position).equals("서울")){
                            adsp_arrive_tm = ArrayAdapter.createFromResource(SearchCity.this,R.array.spinner_tm_seoul_airplane, android.R.layout.simple_spinner_dropdown_item);
                        }else if(adsp_arrive_city.getItem(position).equals("인천")) {
                            adsp_arrive_tm = ArrayAdapter.createFromResource(SearchCity.this, R.array.spinner_tm_incheon_airplane, android.R.layout.simple_spinner_dropdown_item);
                        }else if(adsp_arrive_city.getItem(position).equals("대구")) {
                            adsp_arrive_tm = ArrayAdapter.createFromResource(SearchCity.this, R.array.spinner_tm_daegu_airplane, android.R.layout.simple_spinner_dropdown_item);
                        }else if(adsp_arrive_city.getItem(position).equals("제주")) {
                            adsp_arrive_tm = ArrayAdapter.createFromResource(SearchCity.this, R.array.spinner_tm_jeju_airplane, android.R.layout.simple_spinner_dropdown_item);
                        }else if(adsp_arrive_city.getItem(position).equals("부산")) {
                            adsp_arrive_tm = ArrayAdapter.createFromResource(SearchCity.this, R.array.spinner_tm_busan_airplane, android.R.layout.simple_spinner_dropdown_item);
                        }else if(adsp_arrive_city.getItem(position).equals("울산")) {
                            adsp_arrive_tm = ArrayAdapter.createFromResource(SearchCity.this, R.array.spinner_tm_ulsan_airplane, android.R.layout.simple_spinner_dropdown_item);
                        }else if(adsp_arrive_city.getItem(position).equals("광주")) {
                            adsp_arrive_tm = ArrayAdapter.createFromResource(SearchCity.this, R.array.spinner_tm_gwangju_airplane, android.R.layout.simple_spinner_dropdown_item);
                        }
                        adsp_arrive_tm.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        sp_arrive_tm.setAdapter(adsp_arrive_tm);
                        sp_arrive_tm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                arrive_tm = adsp_arrive_tm.getItem(position).toString();
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }
        });

        search_ibtn_back = findViewById(R.id.search_ibtn_back);
        search_btn_lookup = findViewById(R.id.serach_btn_lookup);
        ibt_date = findViewById(R.id.ibt_calendar);

        ibt_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(SearchCity.this,datePicker,calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        search_ibtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchCity.this, MainActivity.class);
                startActivity(intent);
            }
        });

//        //viewPager setting
//        ViewPager viewPager = findViewById(R.id.search_vp);
//        fragmentPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
//
//        TabLayout tabLayout = findViewById(R.id.search_tab_layout);
//        viewPager.setAdapter(fragmentPagerAdapter);
//        tabLayout.setupWithViewPager(viewPager);

    }

    private void updateLabel(){
        String date_format = "MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(date_format, Locale.KOREA);

        TextView search_tv_date = findViewById(R.id.search_tv_date);
        search_tv_date.setText(sdf.format(calendar.getTime()));
    }
}