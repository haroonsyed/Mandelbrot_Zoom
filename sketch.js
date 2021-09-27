function setup() {
  createCanvas(10000, 10000);
  pixelDensity(1);
  loadPixels();
  draw();
}

const draw = () => {

  // The amount of zoom in the mandelbrot set visualization
  let xRange = 2;
  let yRange = 2;

  for (let xCoord=0; xCoord<width; xCoord++) {
    for(let yCoord=0; yCoord<height; yCoord++){
        
      // Remember that complex numbers are of the form a+bi
      // a is x component
      // b is y component
      // Original denotes this is "C"
      let aOriginal = map(xCoord, 0, width, -xRange, xRange);
      let bOriginal = map(yCoord, 0, height, -yRange, yRange);

      // These are the values that will be iterated on
      let a = 0;
      let b = 0;

      // iteration loop of determining if the number converges
      // Test each point in space and determine convergence
      let n = 0;
      let iterLimit = 20;
      let escapeValue = 2;
      for(n=0;n<iterLimit; n++) {
        // Determine iterated x and y axis value.
        // Remember f(0) = C always

        // Z^2 + C = a^2 + 2abi - b^2 + aOriginal + bOriginali
        // = (a^2 - b^2 + a) + (2abi + bi)
        aa = a*a;
        ab = a*b;
        bb = b*b;
        a = (aa - bb) + aOriginal;
        b = (2*ab) + bOriginal;

        //Does it converge?
        if(abs(a) > escapeValue && abs(b) > escapeValue) {
          break;
        }

      }


      //Set color based on convergence
      let red = 50;
      let green = 150;
      let blue = 170;
      let alpha = 255;

      if(n == iterLimit) {
        red=green=blue=0;
      } 
      else if(n > iterLimit * 0.9) {
        red=green=blue=10;
        red += 40;
      }
      else if(n > iterLimit * 0.8) {
        red=green=blue=50;
        red += 40;
      }
      else if( n > iterLimit * 0.7) {
       red=green=blue=100;
       red += 30;
       green+=20;
       blue -= 50;
      }
      else if(n > iterLimit * 0.6) {
        red=green=blue=150;
        green+=20;
        red += 20;
        blue-=50;
      }
      else if(n > iterLimit * 0.6) {
        red=green=blue=180;
        green+=20;
        red += 20;
        blue-=50;
      }


      let pix = (xCoord+yCoord*width) *4;
      pixels[pix + 0] = red;
      pixels[pix + 1] = green;
      pixels[pix + 2] = blue;
      pixels[pix + 3] = alpha;
    }
  }
  updatePixels();
}
