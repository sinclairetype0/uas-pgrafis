var scene = new THREE.Scene();
var camera = new THREE.PerspectiveCamera( 75, window.innerWidth/window.innerHeight, 0.1, 1000 );

var renderer = new THREE.WebGLRenderer();
renderer.setSize( window.innerWidth, window.innerHeight );
document.body.appendChild( renderer.domElement );

var geometry = new THREE.BoxGeometry( 1, 1, 1 );
var material = new THREE.MeshLambertMaterial( {
     color: "#C479D3"
    } );
var cube = new THREE.Mesh( geometry, material );

var light = new THREE.AmbientLight("#53C54B");

light.position.set( 50, 50, 50 );

var spotLight = new THREE.SpotLight("#FF0000");
spotLight.position.set(-30, 60, 60);
spotLight.castShadow = true;

scene.add(spotLight);
scene.add( light );
scene.add( cube );

camera.position.z = 5;

var animate = function () {
    requestAnimationFrame( animate );

    cube.rotation.x += 0.1;
    cube.rotation.y += 0.1;

    renderer.render( scene, camera );
};

animate();