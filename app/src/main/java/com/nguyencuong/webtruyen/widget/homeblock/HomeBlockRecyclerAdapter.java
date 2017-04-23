package com.nguyencuong.webtruyen.widget.homeblock;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nguyencuong.webtruyen.R;
import com.nguyencuong.webtruyen.model.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * The adater for {@link RecyclerView} in {@link HomeBlockView}
 * use with {@link HomeBlockView} #HOME_ITEM_STYLE_GRID #HOME_ITEM_STYLE_LIST_H #HOME_ITEM_STYLE_LIST_V
 * and type is {@link Book}
 * <p>
 * Created by Mr Cuong on 4/15/2017.
 * Email: vancuong2941989@gmail.com
 */

public class HomeBlockRecyclerAdapter extends RecyclerView.Adapter {

    public interface OnItemClickListener {
        void onBookItemClick(View imgPoster, int bookId);
    }

    public static final int ITEM_TYPE_GRID = 0;
    public static final int ITEM_TYPE_LIST_V = 1;
    public static final int ITEM_TYPE_LIST_H = 2;

    private final int itemType;

    // Use on GridViewHolder
    private final int itemWidth;

    // Use on GridViewHolder
    private final int itemHeight;

    private ArrayList<Book> listBooks = new ArrayList<>();

    private OnItemClickListener onItemClickListener;

    public HomeBlockRecyclerAdapter(int itemType, int itemWidth) {
        this.itemType = itemType;
        this.itemWidth = itemWidth;

        itemHeight = itemWidth * 49/33;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = null;
        switch (itemType) {
            case ITEM_TYPE_GRID:
                itemView = inflater.inflate(R.layout.widget_home_block_view_item_grid, parent, false);
                return new GridViewHolder(itemView);
            case ITEM_TYPE_LIST_V:
                itemView = inflater.inflate(R.layout.widget_home_block_view_item_list_v, parent, false);
                return new ListVerticalViewHolder(itemView);
            case ITEM_TYPE_LIST_H:
                itemView = inflater.inflate(R.layout.widget_home_block_view_item_list_h, parent, false);
                return new GridViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof GridViewHolder) {
            ((GridViewHolder) holder).bindView(listBooks.get(position));
        } else if (holder instanceof ListVerticalViewHolder) {
            ((ListVerticalViewHolder) holder).bindView(listBooks.get(position));
        }
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return listBooks.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public Book getBook(int position) {
        if (position > -1 && position < listBooks.size()) {
            return listBooks.get(position);
        }
        return null;
    }

    public void setListBooks(List<Book> list) {
        if (list == null) return;
        listBooks = (ArrayList<Book>) list;
        notifyDataSetChanged();
    }

    public void addAll(List<Book> list) {
        listBooks.addAll(list);
        notifyDataSetChanged();
    }

    public void clear() {
        listBooks.clear();
        notifyDataSetChanged();
    }

    /**
     * View holder of recycler view grid and horizontal;
     */
    public class GridViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView poster;
        private TextView name;
        private TextView chapter;
        private View iconFull;
        private View iconAudio;

        public GridViewHolder(View itemView) {
            super(itemView);

            poster = (ImageView) itemView.findViewById(R.id.item_widget_block_img_poster);
            name = (TextView) itemView.findViewById(R.id.item_widget_block_tv_title);
            chapter = (TextView) itemView.findViewById(R.id.item_widget_block_tv_chapter);
            iconFull = itemView.findViewById(R.id.item_widget_block_tv_full);
            iconAudio = itemView.findViewById(R.id.item_widget_block_tv_audio);

            itemView.setOnClickListener(this);
            poster.setOnClickListener(this);
        }

        public void bindView(Book book) {

            if (itemType == ITEM_TYPE_GRID) {
                poster.getLayoutParams().width = itemWidth;
                poster.getLayoutParams().height = itemHeight;
            }

            Glide.with(itemView.getContext())
                    .load(book.getPoster())
                    .error(R.drawable.default_poster)
                    .into(poster);

            name.setText(book.getName());

            chapter.setText(String.format(
                    itemView.getContext().getResources().getString(R.string.widget_home_block_item_grid_text_chapter),
                    String.valueOf(book.getCurentChapter()),
                    String.valueOf(book.getTotalChapter())
                    ));


            if (book.getCurentChapter() >= book.getTotalChapter()) {
                iconFull.setVisibility(View.VISIBLE);
            } else {
                iconFull.setVisibility(View.GONE);
            }

            if (book.isAudio()) {
                iconAudio.setVisibility(View.VISIBLE);
            } else {
                iconAudio.setVisibility(View.GONE);
            }
        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                int bookId = listBooks.get(getLayoutPosition()).getId();
                onItemClickListener.onBookItemClick(poster, bookId);
            }
        }
    }

    /**
     * View holder of recycler view horizontal;
     */
    public class ListVerticalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView poster;
        private TextView name;
        private RatingBar ratingBar;
        private TextView author;
        private TextView chapter;
        private TextView viewed;
        private View iconFull;
        private View iconAudio;

        public ListVerticalViewHolder(View itemView) {
            super(itemView);

            poster = (ImageView) itemView.findViewById(R.id.item_widget_block_img_poster);
            ratingBar = (RatingBar) itemView.findViewById(R.id.item_widget_block_rating);
            name = (TextView) itemView.findViewById(R.id.item_widget_block_tv_title);
            author = (TextView) itemView.findViewById(R.id.item_widget_block_tv_author);
            viewed = (TextView) itemView.findViewById(R.id.item_widget_block_tv_viewed);
            chapter = (TextView) itemView.findViewById(R.id.item_widget_block_tv_chapter);
            iconFull = itemView.findViewById(R.id.item_widget_block_tv_full);
            iconAudio = itemView.findViewById(R.id.item_widget_block_tv_audio);

            ratingBar.setMax(10);
            itemView.setOnClickListener(this);
        }

        public void bindView(Book book) {

            Glide.with(itemView.getContext())
                    .load(book.getPoster())
                    .error(R.drawable.default_poster)
                    .into(poster);

            name.setText(book.getName());

            ratingBar.setProgress(book.getPointRating() / book.getTotalRating());

            author.setText(String.format(
                    itemView.getContext().getResources().getString(R.string.widget_home_block_item_list_text_author),
                    book.getAuthor()
            ));

            String textChapExtens = "";
            if (book.getCurentChapter() >= book.getTotalChapter()) {
                textChapExtens = " - Full";
            }
            chapter.setText(String.format(
                    itemView.getContext().getResources().getString(R.string.widget_home_block_item_list_text_chapter),
                    String.valueOf(book.getCurentChapter()),
                    String.valueOf(book.getTotalChapter()),
                    textChapExtens
            ));

            viewed.setText(String.format(
                    itemView.getContext().getResources()
                            .getString(R.string.widget_home_block_item_list_text_viewed),
                    String.valueOf(book.getViewed())
            ));

            if (book.getCurentChapter() >= book.getTotalChapter()) {
                iconFull.setVisibility(View.VISIBLE);
            } else {
                iconFull.setVisibility(View.GONE);
            }

            if (book.isAudio()) {
                iconAudio.setVisibility(View.VISIBLE);
            } else {
                iconAudio.setVisibility(View.GONE);
            }
        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                int bookId = listBooks.get(getLayoutPosition()).getId();
                onItemClickListener.onBookItemClick(poster, bookId);
            }
        }
    }
}
