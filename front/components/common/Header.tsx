import React, { useState, useEffect } from 'react';
import Image from 'next/image';
import Link from 'next/link';

import { useDispatch } from 'react-redux';
import { setTheme } from '@/redux/store/themeSlice';

import styles from '@/styles/Header.module.scss';

function Header() {
  const [themeMode, setThemeMode] = useState('');
  const [checked, setChecked] = useState(false);
  const dispatch = useDispatch();

  const changeMode = () => {
    if (themeMode === 'light') {
      setThemeMode('dark');
      dispatch(setTheme('dark'));
      localStorage.setItem('theme', 'dark');
    } else if (themeMode === 'dark') {
      setThemeMode('light');
      dispatch(setTheme('light'));
      localStorage.setItem('theme', 'light');
    }
    setChecked(!checked);
  };

  useEffect(() => {
    document.body.dataset.theme = themeMode;
  }, [themeMode]);

  useEffect(() => {
    const theme = localStorage.getItem('theme') || 'light';
    setThemeMode(theme);
    theme === 'dark' ? setChecked(true) : setChecked(false);
    dispatch(setTheme(themeMode));
  }, []);

  // header 에 들어갈 menu 리스트
  const headerMenu = [
    {
      name: '노래방',
      link: '/',
    },
    {
      name: '인기차트',
      link: '/',
    },
    {
      name: '노래자랑',
      link: '/',
    },
    {
      name: '마이뮤직',
      link: '/',
    },
  ];

  const icons = {
    logo: `icon/header/${themeMode || 'light'}/${
      themeMode || 'light'
    }_logo.svg`,
    mode: `icon/header/${themeMode || 'light'}/${
      themeMode || 'light'
    }_mode_icon.svg`,
    alarm: `icon/header/${themeMode || 'light'}/${
      themeMode || 'light'
    }_alarm_icon.svg`,
    profile: `icon/header/${themeMode || 'light'}/${
      themeMode || 'light'
    }_profile_icon.svg`,
  };

  // menu 리스트 요소에 대한 태그 생성
  const headerMenus = headerMenu.map(menu => (
    <Link href={menu.link} key={menu.name} className={styles.pages}>
      {menu.name}
    </Link>
  ));

  return (
    <header className={styles.header}>
      <div className={styles.logo}>
        <Link href="/">
          <Image src={icons.logo} alt="logoFail" width={70} height={70} />
        </Link>
      </div>
      <div className={styles.menu}>{headerMenus}</div>
      <div className={styles.icons}>
        <div className={styles.icon}>
          <label className={styles.switch} id="cl" htmlFor="toggleSwitch">
            <input
              type="checkbox"
              onChange={changeMode}
              className={styles.checkbox}
              checked={checked}
              id="toggleSwitch"
            />
          </label>
          <Image src={icons.mode} alt="mode" width={20} height={20} />
        </div>
        <div className={styles.icon}>
          <Image src={icons.alarm} alt="alarm" width={20} height={20} />
          <div className={styles.profile}>
            <Image src={icons.profile} alt="profile" width={25} height={25} />
          </div>
        </div>
      </div>
    </header>
  );
}

export default Header;
