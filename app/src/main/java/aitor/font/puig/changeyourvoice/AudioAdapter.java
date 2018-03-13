package aitor.font.puig.changeyourvoice;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Aitor Font on 13/3/18.
 */

public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.AudioHolder> {

    private List<AudioFileClass> audioList;

    public AudioAdapter(List<AudioFileClass> list) {
        audioList = list;
    }

    @Override
    public AudioHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.audio_list_item, parent, false);
        return new AudioHolder(v);
    }

    @Override
    public void onBindViewHolder(AudioHolder holder, int position) {
        holder.tv_title.setText(audioList.get(position).getAudioTitle());
    }

    @Override
    public int getItemCount() {
        return audioList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    static class AudioHolder extends RecyclerView.ViewHolder {

        TextView tv_title;

        AudioHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.audioTitle);
        }
    }
}
