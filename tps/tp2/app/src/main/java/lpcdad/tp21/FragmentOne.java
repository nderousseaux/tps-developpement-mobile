package lpcdad.tp21;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment {

    FragmentOneUpdated context;

    public FragmentOne() {}

    // la première méthode appelée par le fragment quand l'activité ajoute le fragment par le biais du
    // FragmentManager
    // 1
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        //getActivity(); // -> renvoie une référence sur l'activité "mère"
        if(context instanceof FragmentOneUpdated )
            this.context=(FragmentOneUpdated)context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //2
        // ATTENTION : ICI, aucune interface utilisateur n'est rattachée au fragment
        // getView(); -> renvoie null puisque l'UI n'a pas encore été associée au fragment

       //  ((MainActivity)getActivity()).fragmentTwo.msg="Mon message"; -> à proscrire  puisqu'on veut les fragments soient indépendants
    }

    // 3 : c'est là que l'interface utilisateur est rattachée au fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);


        View v=inflater.inflate(R.layout.fragment_one, container, false);
        // ici vous avez accès aux éléments de l'interface utilisateur par le biais de
        //      v.findViewById(R.id.bt1)
        return v;
    }

    // 4 : ici la vue est rattachée au fragment : on y accède par l'objet view

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button bt;
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.updateFragment();
            }
        });

        // mettre à jour l'UI
        // ici l'appel à getView() n'est pas nul (s'il existe une UI)

    }

    interface FragmentOneUpdated {
        void updateFragment();
    }
}
