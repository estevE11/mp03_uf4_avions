package cat.esteve.atc.utils;

public class Vector3f {
	public float x, y, z;
	
	public Vector3f() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	
	public Vector3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void add(float x, float y, float z) {
		this.x += x;
		this.y += y;
		this.z += z;
	}

	public void add(Vector3f vec) {
		this.add(vec.x, vec.y, vec.z);
	}

	public void sub(Vector3f vec) {
		this.sub(vec.x, vec.y, vec.z);
	}
	
	public void sub(float x, float y, float z) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
	}
	
	public void mul(Vector3f vec) {
		this.mul(vec.x, vec.y, vec.z);
	}
	
	public void mul(float x, float y, float z) {
		this.x *= x;
		this.y *= y;
		this.z *= z;
	}

	public float dist2D(Vector3f vec) {
		float dx = vec.x - this.x;
		float dy = vec.y - this.y;
		return (float) Math.sqrt(dx*dx + dy*dy);
	}

	public String toString() {
		return "Vec3f(" + this.x + ", " + this.y + ", " + this.z + ")";
	}
}
