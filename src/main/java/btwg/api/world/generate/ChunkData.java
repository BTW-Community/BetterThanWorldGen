package btwg.api.world.generate;

public class ChunkData {
    private final short[] data;
    private final int height;
    
    public ChunkData(int height) {
        if (height <= 0 || (height & (height - 1)) != 0 || height > 256) {
            throw new IllegalArgumentException("Height must be a power of two less than or equal to 256!");
        }
        
        this.height = height;
        this.data = new short[16 * 16 * height];
    }
    
    public BlockState getBlock(int x, int y, int z) {
        int index = this.getIndex(x, y, z);
        short data = this.data[index];
        
        return new BlockState((short) (data & 0xFFF), (byte) (data >> 12));
    }
    
    public ChunkData setBlock(int x, int y, int z, short blockID) {
        return this.setBlock(x, y, z, blockID, (byte) 0);
    }
    
    public ChunkData setBlock(int x, int y, int z, short blockID, byte meta) {
        int index = this.getIndex(x, y, z);
        this.data[index] = (short) (blockID & 0xFFF);
        this.data[index] = (short) (this.data[index] | (meta << 12));
        return this;
    }
    
    private int getIndex(int x, int y, int z) {
        this.validateCoords(x, y, z);
        return (x << 4) * this.height | z * this.height | y;
    }
    
    private boolean validateCoords(int x, int y, int z) {
        if (x >= 16 || x < 0) {
            throw new IllegalArgumentException("Value for X " + x + " is outside of valid coordinates [0, 15]");
        }
    
        if (z >= 16 || z < 0) {
            throw new IllegalArgumentException("Value for Z " + x + " is outside of valid coordinates [0, 15]");
        }
        
        if (y >= this.height || y < 0) {
            throw new IllegalArgumentException("Value for Y " + x + " is outside of valid coordinates [0, " + (this.height - 1) + "]");
        }
        
        return true;
    }
    
    public record BlockState(short blockID, byte meta) {}
}
