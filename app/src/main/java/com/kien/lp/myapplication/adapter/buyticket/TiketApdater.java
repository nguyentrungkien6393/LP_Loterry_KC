package com.kien.lp.myapplication.adapter.buyticket;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SyncStatusObserver;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kien.lp.myapplication.R;
import com.kien.lp.myapplication.fragment.Fragment_Choose_2;
import com.kien.lp.myapplication.fragment.Fragment_Choose_HowtoPlay;
import com.kien.lp.myapplication.layout_activity.Activity_HowtoPlay;
import com.kien.lp.myapplication.mysingleton.MySingleTon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TiketApdater extends RecyclerView.Adapter<TiketApdater.TicketHolder> {
    private List<Ticket> tickets;
    private Activity context;
    //    private Activity
    private MySingleTon mySingleTon = MySingleTon.getInstance();
    private HashMap<Integer, Ticket> mapTicket = new HashMap<>();
    private FragmentTransaction fragTransaction;
    private FragmentManager fragmentManager;
    public int t = 10;
    public int t1 = 10;

    public TiketApdater(Activity context, List<Ticket> tickets) {
        this.context = context;
        this.tickets = tickets;
    }

    @NonNull
    @Override
    public TiketApdater.TicketHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_table_choose, parent, false);
        return new TicketHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TiketApdater.TicketHolder holder, final int position) {
        final Ticket ticket = tickets.get(position);
        ticket.curent_select_number = 0;
        ticket.curent_select_number_special = 0;
        if (ticket.isSelted() == true) {
//            Toast.makeText(context, "Chọn Vé Khác", Toast.LENGTH_SHORT).show();
            holder.line_background1.setBackgroundColor(Color.parseColor("#84C0F2"));
            holder.mBackground_item.setBackgroundColor(Color.parseColor("#BCDCF5"));
        } else {
//            Toast.makeText(context, "Chưa Chọn Được Vé à ?", Toast.LENGTH_SHORT).show();
            holder.line_background1.setBackgroundColor(Color.parseColor("#FECC45"));
            holder.mBackground_item.setBackgroundColor(Color.parseColor("#FBDA8D"));
        }
        final List<CellTicket> number69 = ticket.getCell69();
        final List<CellTicket> number26 = ticket.getCell26();
        final CellAdapter num69Adapter = new CellAdapter(context, number69, 0);
        final CellAdapter num26Adapter = new CellAdapter(context, number26, 1);

        holder.revNumber69.setLayoutManager(new GridLayoutManager(context, 8));
        holder.revNumber26.setLayoutManager(new GridLayoutManager(context, 8));
        holder.revNumber69.setAdapter(num69Adapter);
        holder.revNumber26.setAdapter(num26Adapter);
        holder.mTV_Quick_Pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("QUICK_PICK", tickets.size() + "");
//                final Handler ha=new Handler();
//                ha.postDelayed(new Runnable() {
                ticket.setSelted(true);
//                    @Override
//                    public void run() {
//                        //call function
//
////                        ha.postDelayed(this, 10000);
//                    }
//                }, 100);


                quickPick();


            }

            private void quickPick() {
                t = ticket.checkFull69(context);
                t1 = ticket.checkFull26(context);
                int random = (int) (Math.random() * 69 + 1);
                int random1 = (int) (Math.random() * 69 + 1);
                int random2 = (int) (Math.random() * 69 + 1);
                int random3 = (int) (Math.random() * 69 + 1);
                int random4 = (int) (Math.random() * 69 + 1);
                number69.get(random).setSeleted(true);
                number69.get(random1).setSeleted(true);
                number69.get(random2).setSeleted(true);
                number69.get(random3).setSeleted(true);
                number69.get(random4).setSeleted(true);
                ticket.addNumber69(t, number69.get(random).number);
                ticket.addNumber69(t, number69.get(random1).number);
                ticket.addNumber69(t, number69.get(random2).number);
                ticket.addNumber69(t, number69.get(random3).number);
                ticket.addNumber69(t, number69.get(random4).number);
                int randomspecial = (int) (Math.random() * 26 + 1);
                ticket.addNumber26(t1, randomspecial);
                number26.get(randomspecial).setSeleted(true);
                holder.line_background1.setBackgroundColor(Color.parseColor("#84C0F2"));
                holder.mBackground_item.setBackgroundColor(Color.parseColor("#BCDCF5"));
//                if (ticket.curent_select_number == 5&&ticket.curent_select_number_special==1) {
//                    holder.line_background1.setBackgroundColor(Color.parseColor("#84C0F2"));
//                    holder.mBackground_item.setBackgroundColor(Color.parseColor("#BCDCF5"));
//                    Log.e("***************","________________________");
//                    ticket.setSelted(true);
//                    mySingleTon.setmList_Ticket(tickets);
////                            Log.e("*********************",mySingleTon.getmList_Ticket().size()+"");
//                }else {
//                    ticket.setSelted(false);
//                    holder.line_background1.setBackgroundColor(Color.parseColor("#FECC45"));
//                    holder.mBackground_item.setBackgroundColor(Color.parseColor("#FBDA8D"));
//                }
                num69Adapter.notifyDataSetChanged();
                num26Adapter.notifyDataSetChanged();
//                int random = (int) (Math.random() * 10 + 1);
//                t = ticket.checkFull69(context);
//                ticket.addNumber69(t, random);
//                Set<Integer> allNumbers = new HashSet<>();
//                Set<Integer> set = new HashSet<>();
//                while (set.size() < 5)
//                {
//                    int random = (int) (Math.random() * 69 + 1);
//                    if (allNumbers.add(random))
//                    {
//                        set.add(random);
//                        ticket.addNumber69(t,random);
//                    }
//                    System.out.println("__________"+random);
//                }

            }
        });
        holder.mTV_Line_Choose_Play.setText(context.getString(R.string.line) + ": " + (position + 1) + "/" + tickets.size());
        holder.mImageView_Exit_Chooose_Number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.finish();
//                Intent intent = new Intent(context, Activity_HowtoPlay.class);
//                context.startActivityForResult(intent,1);
            }
        });

        num69Adapter.setOnItemClickLitien(new CellAdapter.OnItemClickLitien() {
            @Override
            public void onClickIn(View v, int pos) {
                int check = ticket.checkNumberIsSeleted69(number69.get(pos).number);
                if (check == -1) {
                    t = ticket.checkFull69(context);
                    if (t == -1) {
                        Toast.makeText(context, "No more", Toast.LENGTH_SHORT).show();
                    } else {
                        ticket.curent_select_number = ticket.curent_select_number++;
                        ticket.curent_select_number++;
                        ticket.addNumber69(t, number69.get(pos).number);
                        number69.get(pos).setSeleted(true);
                        mapTicket.put(position, ticket);
                        Log.e("CHECK_SIZE_HASHMAP", ticket.curent_select_number + "");
                        mySingleTon.setmHashMap(mapTicket);
                        if (ticket.curent_select_number == 5 && ticket.curent_select_number_special == 1) {
                            holder.line_background1.setBackgroundColor(Color.parseColor("#84C0F2"));
                            holder.mBackground_item.setBackgroundColor(Color.parseColor("#BCDCF5"));
                            Log.e("***************", "________________________");
                            ticket.setSelted(true);
                            mySingleTon.setmList_Ticket(tickets);
//                            Log.e("*********************",mySingleTon.getmList_Ticket().size()+"");
                        } else {
                            ticket.setSelted(false);
                            holder.line_background1.setBackgroundColor(Color.parseColor("#FECC45"));
                            holder.mBackground_item.setBackgroundColor(Color.parseColor("#FBDA8D"));
                        }
                    }
                } else {
                    ticket.setSelted(false);
                    number69.get(pos).setSeleted(false);
                    ticket.curent_select_number--;
                    mapTicket.remove(position);
                    holder.line_background1.setBackgroundColor(Color.parseColor("#FECC45"));
                    holder.mBackground_item.setBackgroundColor(Color.parseColor("#FBDA8D"));
                    System.out.println("______________BIG_DATA____________" + "XXXXXXXXXXXXXXXXXXXXXXXXXX");
                }
                num69Adapter.notifyDataSetChanged();
            }
        });
        num26Adapter.setOnItemClickLitien(new CellAdapter.OnItemClickLitien() {
            @Override
            public void onClickIn(View v, int pos) {
//                Toast.makeText(context, "Number:" + number26.get(pos).number, Toast.LENGTH_SHORT).show();
                int check = ticket.checkNumberIsSeleted26(number26.get(pos).number);
                if (check == -1) {
                    t1 = ticket.checkFull26(context);
                    if (t1 == -1) {
                        Toast.makeText(context, "No more", Toast.LENGTH_SHORT).show();
                    } else {
                        ticket.addNumber26(t1, number26.get(pos).number);
                        number26.get(pos).setSeleted(true);
                        ticket.curent_select_number_special = ticket.curent_select_number_special++;
                        ticket.curent_select_number_special++;
                        Log.e("CHECK_SIZE_HASHMAP", ticket.curent_select_number_special + "");
                        Log.e("Click_Check_Choise", t1 + "");
                        if (ticket.curent_select_number == 5 && ticket.curent_select_number_special == 1) {
                            ticket.setSelted(true);
//                            holder.line_background1.setBackgroundColor(Color.parseColor("#84C0F2"));
//                            holder.mBackground_item.setBackgroundColor(Color.parseColor("#BCDCF5"));
                            mySingleTon.setmList_Ticket(tickets);
//                            Log.e("*********************",mySingleTon.getmList_Ticket().size()+"");
                        } else {
                            ticket.setSelted(false);
//                            holder.line_background1.setBackgroundColor(Color.parseColor("#FECC45"));
//                            holder.mBackground_item.setBackgroundColor(Color.parseColor("#FBDA8D"));
                        }
                    }
                } else {
                    ticket.setSelted(false);
                    number26.get(pos).setSeleted(false);
                    ticket.curent_select_number_special--;
                    ticket.curent_select_number_special = ticket.curent_select_number_special--;
                    Log.e("CHECK_SIZE_HASHMAP", ticket.curent_select_number_special + "");
                    System.out.println("______________BIG_DATA____________" + "XXXXXXXXXXXXXXXXXXXXXXXXXX");
                    holder.line_background1.setBackgroundColor(Color.parseColor("#FECC45"));
                    holder.mBackground_item.setBackgroundColor(Color.parseColor("#FBDA8D"));
                }
                num26Adapter.notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }


    class TicketHolder extends RecyclerView.ViewHolder {
        RecyclerView revNumber69;
        RecyclerView revNumber26;
        ImageView mImageView_Exit_Chooose_Number;
        TextView mTV_Line_Choose_Play, mTV_Quick_Pick;
        LinearLayout line_background1, mBackground_item;

        public TicketHolder(View itemView) {
            super(itemView);
            revNumber69 = itemView.findViewById(R.id.mRecyclerview1);
            revNumber26 = itemView.findViewById(R.id.mRecyclerview2);
            mImageView_Exit_Chooose_Number = itemView.findViewById(R.id.mImageView_Exit_Chooose_Number);
            mTV_Line_Choose_Play = itemView.findViewById(R.id.mTV_Line_Choose_Play);
            line_background1 = itemView.findViewById(R.id.line_background1);
            mBackground_item = itemView.findViewById(R.id.mBackground_item);
            mTV_Quick_Pick = itemView.findViewById(R.id.mTV_Quick_Pick);
        }
    }
}
