package com.legal.management;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.Intent;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class LawyerProfileViewActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private HashMap<String, Object> map = new HashMap<>();
	private String lname = "";
	private String lamount = "";
	private String laddress = "";
	private String lcontact = "";
	
	private ScrollView vscroll1;
	private LinearLayout linear1;
	private LinearLayout linear2;
	private ImageView imageview1;
	private LinearLayout linear3;
	private TextView textview5;
	private TextView bio;
	private LinearLayout linear14;
	private TextView textview13;
	private LinearLayout linear10;
	private LinearLayout linear12;
	private LinearLayout linear6;
	private TextView name;
	private LinearLayout linear4;
	private ImageView imageview3;
	private TextView textview3;
	private LinearLayout linear5;
	private TextView textview4;
	private ImageView imageview2;
	private TextView textview17;
	private TextView address;
	private ImageView imageview4;
	private LinearLayout linear11;
	private TextView textview15;
	private TextView number;
	private ImageView imageview5;
	private LinearLayout linear13;
	private TextView textview16;
	private TextView mail;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private LinearLayout linear9;
	private TextView textview7;
	private TextView textview8;
	private TextView textview9;
	private TextView experience;
	private TextView textview11;
	private TextView textview12;
	private TextView price;
	private Button button1;
	
	private Intent i = new Intent();
	private DatabaseReference lawyer = _firebase.getReference("lawyer");
	private ChildEventListener _lawyer_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.lawyer_profile_view);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_app_bar = findViewById(R.id._app_bar);
		_coordinator = findViewById(R.id._coordinator);
		_toolbar = findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		vscroll1 = findViewById(R.id.vscroll1);
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		imageview1 = findViewById(R.id.imageview1);
		linear3 = findViewById(R.id.linear3);
		textview5 = findViewById(R.id.textview5);
		bio = findViewById(R.id.bio);
		linear14 = findViewById(R.id.linear14);
		textview13 = findViewById(R.id.textview13);
		linear10 = findViewById(R.id.linear10);
		linear12 = findViewById(R.id.linear12);
		linear6 = findViewById(R.id.linear6);
		name = findViewById(R.id.name);
		linear4 = findViewById(R.id.linear4);
		imageview3 = findViewById(R.id.imageview3);
		textview3 = findViewById(R.id.textview3);
		linear5 = findViewById(R.id.linear5);
		textview4 = findViewById(R.id.textview4);
		imageview2 = findViewById(R.id.imageview2);
		textview17 = findViewById(R.id.textview17);
		address = findViewById(R.id.address);
		imageview4 = findViewById(R.id.imageview4);
		linear11 = findViewById(R.id.linear11);
		textview15 = findViewById(R.id.textview15);
		number = findViewById(R.id.number);
		imageview5 = findViewById(R.id.imageview5);
		linear13 = findViewById(R.id.linear13);
		textview16 = findViewById(R.id.textview16);
		mail = findViewById(R.id.mail);
		linear7 = findViewById(R.id.linear7);
		linear8 = findViewById(R.id.linear8);
		linear9 = findViewById(R.id.linear9);
		textview7 = findViewById(R.id.textview7);
		textview8 = findViewById(R.id.textview8);
		textview9 = findViewById(R.id.textview9);
		experience = findViewById(R.id.experience);
		textview11 = findViewById(R.id.textview11);
		textview12 = findViewById(R.id.textview12);
		price = findViewById(R.id.price);
		button1 = findViewById(R.id.button1);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), BookAppointmentActivity.class);
				i.putExtra("uid", getIntent().getStringExtra("uid"));
				i.putExtra("lawyer name", lname);
				i.putExtra("lawyer contact", lcontact);
				i.putExtra("lawyer address", laddress);
				i.putExtra("amount", lamount);
				startActivity(i);
			}
		});
		
		_lawyer_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.get("uid").toString().equals(getIntent().getStringExtra("uid"))) {
					name.setText(_childValue.get("lawyer name").toString());
					bio.setText(_childValue.get("lawyer bio").toString());
					number.setText(_childValue.get("lawyer contact").toString());
					mail.setText(_childValue.get("lawyer email").toString());
					experience.setText(_childValue.get("lawyer experience").toString());
					price.setText("price:- ".concat(_childValue.get("amount").toString().concat("Rs")));
					address.setText(_childValue.get("lawyer address").toString());
					lname = _childValue.get("lawyer name").toString();
					lamount = _childValue.get("amount").toString();
					laddress = _childValue.get("lawyer address").toString();
					lcontact = _childValue.get("lawyer contact").toString();
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.get("uid").toString().equals(getIntent().getStringExtra("uid"))) {
					name.setText(_childValue.get("lawyer name").toString());
					bio.setText(_childValue.get("lawyer bio").toString());
					number.setText(_childValue.get("lawyer contact").toString());
					mail.setText(_childValue.get("lawyer email").toString());
					experience.setText(_childValue.get("lawyer experience").toString());
					price.setText("price:- ".concat(_childValue.get("amount").toString().concat("Rs")));
					address.setText(_childValue.get("lawyer address").toString());
				}
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		lawyer.addChildEventListener(_lawyer_child_listener);
	}
	
	private void initializeLogic() {
		_Round(20, 20, 20, 20, "#FFFFFF", linear3);
		_Round(20, 20, 20, 20, "#33b5e5", button1);
		_rippleRoundStroke(linear3, "#FFFFFF", "#FFFFFF", 20, 1, "#000000");
		_Round(20, 20, 20, 20, "#FFFFFF", linear6);
		_rippleRoundStroke(linear6, "#FFFFFF", "#FFFFFF", 20, 1, "#000000");
		_rippleRoundStroke(linear7, "#FFFFFF", "#FFFFFF", 5, 1, "#000000");
		_rippleRoundStroke(linear8, "#FFFFFF", "#FFFFFF", 5, 1, "#000000");
		_rippleRoundStroke(linear9, "#FFFFFF", "#FFFFFF", 5, 1, "#000000");
	}
	
	public void _rippleRoundStroke(final View _view, final String _focus, final String _pressed, final double _round, final double _stroke, final String _strokeclr) {
		android.graphics.drawable.GradientDrawable GG = new android.graphics.drawable.GradientDrawable();
		GG.setColor(Color.parseColor(_focus));
		GG.setCornerRadius((float)_round);
		GG.setStroke((int) _stroke,
		Color.parseColor("#" + _strokeclr.replace("#", "")));
		android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor(_pressed)}), GG, null);
		_view.setBackground(RE);
	}
	
	
	public void _Round(final double _one, final double _two, final double _three, final double _four, final String _color, final View _view) {
		Double left_top = _one;
		Double right_top = _two;
		Double left_bottom = _three;
		Double right_bottom = _four;
		android.graphics.drawable.GradientDrawable s = new android.graphics.drawable.GradientDrawable();
		s.setShape(android.graphics.drawable.GradientDrawable.RECTANGLE);
		s.setCornerRadii(new float[] {left_top.floatValue(),left_top.floatValue(), right_top.floatValue(),right_top.floatValue(), left_bottom.floatValue(),left_bottom.floatValue(), right_bottom.floatValue(),right_bottom.floatValue()});
		s.setColor(Color.parseColor(_color));
		_view.setBackground(s);
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}