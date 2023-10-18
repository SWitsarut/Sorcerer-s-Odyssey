#include <iostream>
#include <cstdlib>
#include <cstring>
#include <sys/stat.h> // For stat() function
#include <dirent.h>

bool isOggFile(const char *filename)
{
    // Check if the file has a .ogg extension
    const char *fileExtension = strrchr(filename, '.');
    return (fileExtension != nullptr && strcmp(fileExtension, ".ogg") == 0);
}

void convertOggToWav(const char *inputPath, const char *outputPath)
{
    // Find the position of the last '.' character in the input file name
    const char *lastDot = strrchr(inputPath, '.');

    if (lastDot != nullptr)
    {
        // Calculate the length of the filename without the extension
        size_t filenameLength = lastDot - inputPath;

        // Copy the filename without the extension to the output path
        char outputFileBase[256];
        strncpy(outputFileBase, inputPath, filenameLength);
        outputFileBase[filenameLength] = '\0';

        // Build the FFmpeg command with the correct output file name
        char ffmpegCommand[1024];
        snprintf(ffmpegCommand, sizeof(ffmpegCommand), "ffmpeg -i \"%s\" \"%s.wav\"", inputPath, outputFileBase);

        int result = std::system(ffmpegCommand);
        if (result == 0)
        {
            std::cout << "Converted " << inputPath << " to " << outputFileBase << ".wav" << std::endl;
        }
        else
        {
            std::cerr << "Error converting " << inputPath << " to " << outputFileBase << ".wav" << std::endl;
        }
    }
    else
    {
        std::cerr << "Invalid input file format: " << inputPath << std::endl;
    }
}

void convertWavTo16Bit(const char *inputPath, const char *outputPath)
{
    // Build the FFmpeg command with the correct output file name and audio format
    char ffmpegCommand[1024];
    snprintf(ffmpegCommand, sizeof(ffmpegCommand), "ffmpeg -i \"%s\" -acodec pcm_s16le \"%s\"", inputPath, outputPath);

    int result = std::system(ffmpegCommand);
    if (result == 0)
    {
        std::cout << "Converted " << inputPath << " to " << outputPath << std::endl;
    }
    else
    {
        std::cerr << "Error converting " << inputPath << " to " << outputPath << std::endl;
    }
}

// int main()
// {
//     const char *srcDir = "./";      // Current directory
//     const char *destDir = "./wav/"; // Subdirectory named "wav" within the current directory

//     DIR *dir;
//     struct dirent *ent;

//     if ((dir = opendir(srcDir)) != NULL)
//     {
//         while ((ent = readdir(dir)) != NULL)
//         {
//             const char *filename = ent->d_name;

//             // Check if it's a regular file and has a .ogg extension
//             if (isOggFile(filename))
//             {
//                 char inputPath[256];
//                 char outputPath[256];
//                 snprintf(inputPath, sizeof(inputPath), "%s%s", srcDir, filename);
//                 snprintf(outputPath, sizeof(outputPath), "%s%s.wav", destDir, filename);

//                 convertOggToWav(inputPath, outputPath);
//             }
//         }
//         closedir(dir);
//     }
//     else
//     {
//         perror("Error opening directory");
//         return 1;
//     }

//     return 0;
// }
int main()
{
    const char *srcDir = "./";                            // Source directory (current directory)
    const char *outputFolder = "/path/to/output_folder/"; // Specify the desired output folder
    const char *destDir = "./wav/";                       // Subdirectory within the output folder

    DIR *dir;
    struct dirent *ent;

    if ((dir = opendir(srcDir)) != NULL)
    {
        while ((ent = readdir(dir)) != NULL)
        {
            const char *filename = ent->d_name;

            // Check if it's a regular file and has a .wav extension
            if (strstr(filename, ".wav") != nullptr)
            {
                char inputPath[256];
                char outputPath[256];
                snprintf(inputPath, sizeof(inputPath), "%s%s", srcDir, filename);
                snprintf(outputPath, sizeof(outputPath), "%s%s.wav", outputFolder, filename);

                convertWavTo16Bit(inputPath, outputPath);
            }
        }
        closedir(dir);
    }
    else
    {
        perror("Error opening directory");
        return 1;
    }

    return 0;
}