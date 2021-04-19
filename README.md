# ScannerView

初始化项目

Usage Demo:

Step 1:

<TextView
    android:id="@+id/button_view"
    android:gravity="center"
    android:layout_width="200dp"
    android:layout_height="48dp"
    android:text="扫一扫"
    android:textSize="16dp"
    android:textColor="#ffffff"
    android:background="#4285f4"/>
    
 Step 2:
 
 private TextView button_view;
 
 public class MainActivity extends AppCompatActivity implements OnResultEventListener {
 @Override
    protected void onCreate(Bundle savedInstanceState) {
    ...
    button_view = findViewById(R.id.button_view);
    button_view.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(ContextCompat.checkSelfPermission(
                    MainActivity.this,
                    Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                startActivityForResult(intent, 100);
            }else{
                requestPermission();
            }
        }
    });
    
    private void requestPermission(){
        requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
    }
    
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if(ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                    startActivityForResult(intent, 1);
                }else{
                    Toast.makeText(this, "只有允许相机权限才能使用", Toast.LENGTH_SHORT);
                }
                break;
        }
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data != null){
            switch (requestCode){
                case 100:
                    Bundle bundle = data.getExtras();
                    ResultModel resultModel = bundle.getParcelable("resultModel");
                    getResult(resultModel);
                    break;
            }
        }
    }
    
    @Override
    public void getResult(ResultModel resultModel) {
        try {

            String result = resultModel.getResult();
            ...
            
            Your logic code.

        } catch (JSONException exception){

        }
    }
