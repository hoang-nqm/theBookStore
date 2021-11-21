USE [master]
GO
/****** Object:  Database [TheBookStore]    Script Date: 6/22/2021 6:52:24 PM ******/
CREATE DATABASE [TheBookStore]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'TheBookStore', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL13.SQLEXPRESS\MSSQL\DATA\TheBookStore.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'TheBookStore_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL13.SQLEXPRESS\MSSQL\DATA\TheBookStore_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [TheBookStore] SET COMPATIBILITY_LEVEL = 130
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [TheBookStore].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [TheBookStore] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [TheBookStore] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [TheBookStore] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [TheBookStore] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [TheBookStore] SET ARITHABORT OFF 
GO
ALTER DATABASE [TheBookStore] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [TheBookStore] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [TheBookStore] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [TheBookStore] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [TheBookStore] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [TheBookStore] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [TheBookStore] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [TheBookStore] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [TheBookStore] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [TheBookStore] SET  DISABLE_BROKER 
GO
ALTER DATABASE [TheBookStore] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [TheBookStore] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [TheBookStore] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [TheBookStore] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [TheBookStore] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [TheBookStore] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [TheBookStore] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [TheBookStore] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [TheBookStore] SET  MULTI_USER 
GO
ALTER DATABASE [TheBookStore] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [TheBookStore] SET DB_CHAINING OFF 
GO
ALTER DATABASE [TheBookStore] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [TheBookStore] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [TheBookStore] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [TheBookStore] SET QUERY_STORE = OFF
GO
USE [TheBookStore]
GO
ALTER DATABASE SCOPED CONFIGURATION SET LEGACY_CARDINALITY_ESTIMATION = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION SET MAXDOP = 0;
GO
ALTER DATABASE SCOPED CONFIGURATION SET PARAMETER_SNIFFING = ON;
GO
ALTER DATABASE SCOPED CONFIGURATION SET QUERY_OPTIMIZER_HOTFIXES = OFF;
GO
USE [TheBookStore]
GO
/****** Object:  Table [dbo].[Books]    Script Date: 6/22/2021 6:52:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Books](
	[bookID] [nvarchar](50) NOT NULL,
	[title] [nvarchar](50) NULL,
	[image] [nvarchar](500) NULL,
	[description] [nvarchar](500) NULL,
	[price] [float] NULL,
	[author] [nvarchar](50) NULL,
	[categoryID] [varchar](50) NULL,
	[status] [varchar](10) NULL,
	[quantity] [int] NULL,
	[importDate] [date] NULL,
 CONSTRAINT [PK_Books] PRIMARY KEY CLUSTERED 
(
	[bookID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 6/22/2021 6:52:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[categoryID] [varchar](50) NOT NULL,
	[categoryName] [nvarchar](50) NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[categoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Details]    Script Date: 6/22/2021 6:52:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Details](
	[detailID] [int] IDENTITY(1,1) NOT NULL,
	[price] [float] NULL,
	[quantity] [int] NULL,
	[OrderID] [int] NULL,
	[bookID] [nvarchar](50) NULL,
 CONSTRAINT [PK__Details__83077839AFBFCB3E] PRIMARY KEY CLUSTERED 
(
	[detailID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 6/22/2021 6:52:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[OrderID] [int] IDENTITY(1,1) NOT NULL,
	[DateOrder] [date] NULL,
	[total] [float] NULL,
	[userID] [nvarchar](50) NULL,
 CONSTRAINT [PK__Orders__C3905BAF3B920F7C] PRIMARY KEY CLUSTERED 
(
	[OrderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 6/22/2021 6:52:24 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[userID] [nvarchar](50) NOT NULL,
	[fullName] [nvarchar](50) NULL,
	[password] [nvarchar](50) NULL,
	[roleID] [nvarchar](50) NULL,
	[status] [varchar](10) NULL,
	[phone] [varchar](15) NULL,
 CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Books] ([bookID], [title], [image], [description], [price], [author], [categoryID], [status], [quantity], [importDate]) VALUES (N'B1', N'The Pragmatic Programmer', N'ThePragmatic.jpg', N'Pragmatic Programmer: From Journeyman to Master is a book on computer programming and software engineering, written by Andrew Hunt and David Thomas and published in October 1999.', 800000, N'David Thomas', N'4', N'Active', 0, CAST(N'2021-06-20' AS Date))
GO
INSERT [dbo].[Books] ([bookID], [title], [image], [description], [price], [author], [categoryID], [status], [quantity], [importDate]) VALUES (N'B2', N'Hoang Tu Be', N'hoangtu.jpg', N'The Little Prince, published in 1943, is the most famous novel by French writer and aviator Antoine de Saint-ExupÃÂ©ry. He rented The Bevin House in Asharoken, New York, Long Island while writing this piece.', 400000, N'asdce', N'3', N'Active', 15, CAST(N'2021-06-20' AS Date))
GO
INSERT [dbo].[Books] ([bookID], [title], [image], [description], [price], [author], [categoryID], [status], [quantity], [importDate]) VALUES (N'B3', N'Bach Da Hanh', N'bachdahanh.jpg', N'The story begins in 1972, with the death of an Osakan pawnbroker, Yosuke Kirihara, under strange circumstances. The investigation, headed by officer Junzo Sasagaki, gradually uncovers Fumiyo Nishimoto, a mother struggling to make ends meet and one of his customers, and her boyfriend Tadao Terasaki, as prime suspects. ', 820000, N'Higashino Keigo', N'1', N'Active', 4, CAST(N'2021-06-20' AS Date))
GO
INSERT [dbo].[Books] ([bookID], [title], [image], [description], [price], [author], [categoryID], [status], [quantity], [importDate]) VALUES (N'B4', N'Phia sau nghi can X', N'phiasau.jpg', N'Behind suspect X is a series of novels written in 2005 by Higashino Keigo, the third book in the Detective Galileo series and this is his most successful work ever.', 500000, N'Higashino Keigo', N'2', N'Active', 12, CAST(N'2021-06-20' AS Date))
GO
INSERT [dbo].[Books] ([bookID], [title], [image], [description], [price], [author], [categoryID], [status], [quantity], [importDate]) VALUES (N'B5', N'De Men Phieu Luu Ki', N'demen.jpg', N'Behind suspect X is a series of novels written in 2005 by Higashino Keigo, the third book in the Detective Galileo series and this is his most successful work ever.', 200000, N'To Hoai', N'3', N'Active', 3, CAST(N'2021-06-20' AS Date))
GO
INSERT [dbo].[Books] ([bookID], [title], [image], [description], [price], [author], [categoryID], [status], [quantity], [importDate]) VALUES (N'B6', N'The Clean Coder', N'thecleancoder.jpg', N'All in all, The Clean Coder is a book worth reading. Whether you are a fresh graduate or have been working for a year or two; Whether you are a lowly dev or a team leader or a project manager, the knowledge in the book is extremely useful to you.', 400000, N'Robert Martin', N'4', N'Active', 4, CAST(N'2021-06-20' AS Date))
GO
INSERT [dbo].[Books] ([bookID], [title], [image], [description], [price], [author], [categoryID], [status], [quantity], [importDate]) VALUES (N'B7', N'Code Complete', N'CodeComplete.jpg', N'McConnell defines the main activities in construction as detailed design, construction planning, coding and debugging, unit testing, integration and integration testing. Although he does not dismiss the value of other aspects of software development such as requirements and documentation, McConnell emphasises the construction of software due to several reasons. ', 920000, N'Steve McConnell', N'4', N'Active', 5, CAST(N'2021-06-20' AS Date))
GO
INSERT [dbo].[Category] ([categoryID], [categoryName]) VALUES (N'1', N'Truyện, tiểu thuyết')
GO
INSERT [dbo].[Category] ([categoryID], [categoryName]) VALUES (N'2', N'Tâm lý, tâm linh, tôn giáo')
GO
INSERT [dbo].[Category] ([categoryID], [categoryName]) VALUES (N'3', N'Sách thiếu nhi')
GO
INSERT [dbo].[Category] ([categoryID], [categoryName]) VALUES (N'4', N'Sách lập trình')
GO
SET IDENTITY_INSERT [dbo].[Details] ON 
GO
INSERT [dbo].[Details] ([detailID], [price], [quantity], [OrderID], [bookID]) VALUES (1, 800000, 1, 1, N'B1')
GO
INSERT [dbo].[Details] ([detailID], [price], [quantity], [OrderID], [bookID]) VALUES (2, 800000, 1, 2, N'B1')
GO
INSERT [dbo].[Details] ([detailID], [price], [quantity], [OrderID], [bookID]) VALUES (3, 200000, 1, 3, N'B5')
GO
INSERT [dbo].[Details] ([detailID], [price], [quantity], [OrderID], [bookID]) VALUES (4, 400000, 1, 3, N'B6')
GO
SET IDENTITY_INSERT [dbo].[Details] OFF
GO
SET IDENTITY_INSERT [dbo].[Orders] ON 
GO
INSERT [dbo].[Orders] ([OrderID], [DateOrder], [total], [userID]) VALUES (1, CAST(N'2021-06-21' AS Date), 800000, N'minhhoang@gmail.com')
GO
INSERT [dbo].[Orders] ([OrderID], [DateOrder], [total], [userID]) VALUES (2, CAST(N'2021-06-22' AS Date), 800000, N'minhhoang@gmail.com')
GO
INSERT [dbo].[Orders] ([OrderID], [DateOrder], [total], [userID]) VALUES (3, CAST(N'2021-06-22' AS Date), 600000, N'minhhoang@gmail.com')
GO
SET IDENTITY_INSERT [dbo].[Orders] OFF
GO
INSERT [dbo].[Users] ([userID], [fullName], [password], [roleID], [status], [phone]) VALUES (N'admin@gmail.com', N'admin', N'123456', N'1', N'Active', N'0356449764')
GO
INSERT [dbo].[Users] ([userID], [fullName], [password], [roleID], [status], [phone]) VALUES (N'minhhoang@gmail.com', N'Minh Hoang', N'123456', N'2', N'Active', N'0356449764')
GO
ALTER TABLE [dbo].[Books]  WITH CHECK ADD  CONSTRAINT [FK_Books_Category] FOREIGN KEY([categoryID])
REFERENCES [dbo].[Category] ([categoryID])
GO
ALTER TABLE [dbo].[Books] CHECK CONSTRAINT [FK_Books_Category]
GO
ALTER TABLE [dbo].[Details]  WITH CHECK ADD  CONSTRAINT [FK_Details_Books] FOREIGN KEY([bookID])
REFERENCES [dbo].[Books] ([bookID])
GO
ALTER TABLE [dbo].[Details] CHECK CONSTRAINT [FK_Details_Books]
GO
ALTER TABLE [dbo].[Details]  WITH CHECK ADD  CONSTRAINT [FK_Details_Orders] FOREIGN KEY([OrderID])
REFERENCES [dbo].[Orders] ([OrderID])
GO
ALTER TABLE [dbo].[Details] CHECK CONSTRAINT [FK_Details_Orders]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK_Orders_Users] FOREIGN KEY([userID])
REFERENCES [dbo].[Users] ([userID])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK_Orders_Users]
GO
USE [master]
GO
ALTER DATABASE [TheBookStore] SET  READ_WRITE 
GO
