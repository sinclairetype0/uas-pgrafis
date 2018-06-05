var scene = new THREE.Scene();
var camera = new THREE.PerspectiveCamera( 75, window.innerWidth/window.innerHeight, 0.1, 1000 );

var renderer = new THREE.WebGLRenderer();
renderer.setSize( window.innerWidth, window.innerHeight );
document.body.appendChild( renderer.domElement );

var geometry = new THREE.BoxGeometry( 1, 8, 1 );
var material = new THREE.MeshLambertMaterial( {
     color: "#C479D3"
    } );

// MAIN fondasi init mesh with box geometry object
var fondasi = new THREE.Mesh( geometry, material );
var fondasi_two = fondasi.clone();
var fondasi_three = fondasi.clone();
var fondasi_four = fondasi.clone();

const jarak_fondasi_x = 35;
const jarak_fondasi_z = -5;
// set positioning fondasi
fondasi_two.position.z = jarak_fondasi_z;
fondasi_three.position.x = jarak_fondasi_x;
fondasi_four.position.z = jarak_fondasi_z;
fondasi_four.position.x = jarak_fondasi_x;

var fondasi_group = new THREE.Group();
fondasi_group.add(fondasi);
fondasi_group.add(fondasi_two);
fondasi_group.add(fondasi_three);
fondasi_group.add(fondasi_four);

// V shape
var vShape_x = 0;
var vShape_y = 0;

var vShape = new THREE.Shape();
vShape.moveTo(0, 0)
vShape.lineTo(-1, 0)
vShape.lineTo(-2, 3)
vShape.lineTo(-1, 3)
// vShape.lineTo(-3, 3)
// vShape.lineTo(3, 3)
// vShape.lineTo(1, 0)

var extrudeSettings = {
    steps: 1,
    depth: 1,
    bevelEnabled: true,
    bevelThickness: 0,
    bevelSize: 0,
    bevelSegments: 0
};
// init v shape
var extrude_vshape = new THREE.ExtrudeBufferGeometry(vShape, extrudeSettings)
var vShape_mesh = new THREE.Mesh(extrude_vshape, material)
var vShape_mesh2 = vShape_mesh.clone()
vShape_mesh2.rotation.y = Math.PI
vShape_mesh2.position.z = 1;


vShape_group = new THREE.Group()
vShape_group.add(vShape_mesh)
vShape_group.add(vShape_mesh2)

vShape_group.position.x = 5
vShape_group.position.y = -4

vShape_group2 = vShape_group.clone()
vShape_group3 = vShape_group.clone()
vShape_group4 = vShape_group.clone()
vShape_group5 = vShape_group.clone()
vShape_group6 = vShape_group.clone()
vShape_group.position.z = jarak_fondasi_z;

vShape_group3.position.x = jarak_fondasi_x/2
vShape_group4.position.x = jarak_fondasi_x/2
vShape_group4.position.z = jarak_fondasi_z
vShape_group5.position.x = jarak_fondasi_x-5
vShape_group6.position.x = jarak_fondasi_x - 5
vShape_group6.position.z = jarak_fondasi_z


vShapeMaster = new THREE.Group()
vShapeMaster.add(vShape_group)
vShapeMaster.add(vShape_group2)
vShapeMaster.add(vShape_group3)
vShapeMaster.add(vShape_group4)
vShapeMaster.add(vShape_group5)
vShapeMaster.add(vShape_group6)
// fondasi_three.position.x = jarak_fondasi_x;
// fondasi_four.position.z = jarak_fondasi_z;
// fondasi_four.position.x = jarak_fondasi_x;


var light = new THREE.AmbientLight("#53C54B");
light.position.set( 50, 50, 50 );

var spotLight = new THREE.SpotLight("#FF0000");
spotLight.position.set(-30, 60, 60);
spotLight.castShadow = true;

scene.add(spotLight);
scene.add( light );
scene.add(fondasi_group);
scene.add(vShapeMaster)

var axesHelper = new THREE.AxesHelper(5);
scene.add(axesHelper);

camera.position.z = 20;
camera.position.x = jarak_fondasi_x/2;
camera.position.y = 10;
// camera.lookAt(0,0,1)
// renderer.render(scene, camera);
var animate = function () {
    requestAnimationFrame( animate );
    // vShape_mesh.rotation.x += 0.01;
    // fondasi.rotation.x += 0.1;
    // fondasi.rotation.y += 0.1;
    // camera.rotation.y += 0.01;
    // vShape_mesh2.rotation.y += 0.01
    renderer.render( scene, camera );
};

animate();